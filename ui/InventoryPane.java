package ui;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import repository.Repository;
import Items.Rune.Rune;
import Items.Weapon.LHandWeapon;
import Items.Weapon.RHandWeapon;

import java.util.List;

public class InventoryPane extends TabPane {

    private static final int TILE_SIZE = 80;
    private static final int TILE_HGAP = 10;
    private static final int TILE_VGAP = 10;
    private static final int TILE_PADDING = 10;
    private BorderPane root;

    public InventoryPane(Repository repo) {
        root = new BorderPane();
        root.setCenter(this);

        Tab rHandTab = new Tab("Right-Hand Weapons", createRHandWeaponTilePane(repo.getRHandWeapons()));
        Tab lHandTab = new Tab("Left-Hand Weapons", createLHandWeaponTilePane(repo.getLHandWeapons()));
        Tab runesTab = new Tab("Runes", createRuneTilePane(repo.getRunes()));
        Tab headArmorTab = new Tab("Head Armor", createArmorTilePane(repo.getHeadArmors()));
        Tab chestArmorTab = new Tab("Chest Armor", createArmorTilePane(repo.getChestArmors()));
        Tab legArmorTab = new Tab("Leg Armor", createArmorTilePane(repo.getLegArmors()));
        Tab handArmorTab = new Tab("Hand Armor", createArmorTilePane(repo.getHandArmors()));

        for (Tab tab : List.of(rHandTab, lHandTab, runesTab, headArmorTab, chestArmorTab, legArmorTab, handArmorTab)) {
            tab.setClosable(false);
        }

        this.getTabs().addAll(rHandTab, lHandTab, runesTab, headArmorTab, chestArmorTab, legArmorTab, handArmorTab);

        root.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
    }

    public Parent getRoot() {
        return root;
    }

    private ScrollPane createRHandWeaponTilePane(List<RHandWeapon> weapons) {
        TilePane tile = new TilePane(TILE_HGAP, TILE_VGAP);
        tile.setPadding(new Insets(TILE_PADDING));
        tile.setPrefColumns(4);

        for (RHandWeapon weapon : weapons) {
            VBox vbox = createItemBox(weapon.getName(), weapon.getImageSrc());
            tile.getChildren().add(vbox);
        }

        ScrollPane scroll = new ScrollPane(tile);
        scroll.setFitToWidth(true);
        return scroll;
    }

    private ScrollPane createLHandWeaponTilePane(List<LHandWeapon> weapons) {
        TilePane tile = new TilePane(TILE_HGAP, TILE_VGAP);
        tile.setPadding(new Insets(TILE_PADDING));
        tile.setPrefColumns(4);

        for (LHandWeapon weapon : weapons) {
            VBox vbox = createItemBox(weapon.getName(), weapon.getImageSrc());
            tile.getChildren().add(vbox);
        }

        ScrollPane scroll = new ScrollPane(tile);
        scroll.setFitToWidth(true);
        return scroll;
    }

    private ScrollPane createRuneTilePane(List<Rune> runes) {
        TilePane tile = new TilePane(TILE_HGAP, TILE_VGAP);
        tile.setPadding(new Insets(TILE_PADDING));
        tile.setPrefColumns(4);

        for (Rune rune : runes) {
            VBox vbox = createItemBox(rune.getName(), rune.getImageSrc());
            tile.getChildren().add(vbox);
        }

        ScrollPane scroll = new ScrollPane(tile);
        scroll.setFitToWidth(true);
        return scroll;
    }

    private <T> ScrollPane createArmorTilePane(List<T> items) {
        TilePane tile = new TilePane(TILE_HGAP, TILE_VGAP);
        tile.setPadding(new Insets(TILE_PADDING));
        tile.setPrefColumns(4);

        for (T item : items) {
            try {
                String name = (String) item.getClass().getMethod("getName").invoke(item);
                String imageSrc = (String) item.getClass().getMethod("getImageSrc").invoke(item);
                VBox vbox = createItemBox(name, imageSrc);
                tile.getChildren().add(vbox);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        ScrollPane scroll = new ScrollPane(tile);
        scroll.setFitToWidth(true);
        return scroll;
    }

    private VBox createItemBox(String name, String imageUrl) {
        ImageView imageView = new ImageView();
        imageView.setFitWidth(TILE_SIZE);
        imageView.setFitHeight(TILE_SIZE);
        imageView.getStyleClass().add("image-view");
        try {
            imageView.setImage(new Image(imageUrl, true));
        } catch (Exception e) {
            System.out.println("Error loading image: " + imageUrl);
        }

        Text label = new Text(name);

        VBox vbox = new VBox(5, imageView, label);
        vbox.getStyleClass().add("vbox");
        vbox.setPadding(new Insets(5));
        vbox.setOnMouseClicked((MouseEvent event) -> {
            System.out.println("Selected: " + name);
        });

        return vbox;
    }
}
