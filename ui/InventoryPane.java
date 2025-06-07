package ui;

import Entitys.Origin;
import Entitys.Personagem;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import repository.Repository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.List;

public class InventoryPane extends TabPane {

    private static final int TILE_SIZE = 100;
    private static final int TILE_HGAP = 10;
    private static final int TILE_VGAP = 10;
    private static final int TILE_PADDING = 10;
    private static final int TILE_COLUMNS = 4;
    private static final String IMAGE_CACHE_DIR = "cached_images";
    private BorderPane root;
    private VBox detailPane;
    private ImageView detailImage;
    private Text detailName;
    private Text detailStats;

    public InventoryPane(Repository repo, Origin origin) {

        Personagem Hunter = new Personagem(origin, repo);

        root = new BorderPane();

        new File(IMAGE_CACHE_DIR).mkdirs();

        VBox tabBox = new VBox();
        tabBox.setPrefWidth(450);
        tabBox.getChildren().add(this);

        Tab rHandTab = new Tab("Right-Hand Weapons", createTilePane(repo.getRHandWeapons()));
        Tab lHandTab = new Tab("Left-Hand Weapons", createTilePane(repo.getLHandWeapons()));
        Tab runesTab = new Tab("Runes", createTilePane(repo.getRunes()));
        Tab headArmorTab = new Tab("Head Armor", createTilePane(repo.getHeadArmors()));
        Tab chestArmorTab = new Tab("Chest Armor", createTilePane(repo.getChestArmors()));
        Tab legArmorTab = new Tab("Leg Armor", createTilePane(repo.getLegArmors()));
        Tab handArmorTab = new Tab("Hand Armor", createTilePane(repo.getHandArmors()));

        for (Tab tab : List.of(rHandTab, lHandTab, runesTab, headArmorTab, chestArmorTab, legArmorTab, handArmorTab)) {
            tab.setClosable(false);
        }

        this.getTabs().addAll(rHandTab, lHandTab, runesTab, headArmorTab, chestArmorTab, legArmorTab, handArmorTab);

        detailImage = new ImageView();
        detailImage.setFitWidth(250);
        detailImage.setFitHeight(250);

        detailName = new Text("Select an item to view details");
        detailStats = new Text("");

        detailPane = new VBox(10, detailImage, detailName, detailStats);
        detailPane.setPadding(new Insets(20));
        detailPane.setPrefWidth(450);

        HBox splitPane = new HBox(tabBox, detailPane);
        root.setCenter(splitPane);

        root.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
    }

    public Parent getRoot() {
        return root;
    }

    private VBox createItemBox(Object item) {
        try {
            Method getName = item.getClass().getMethod("getName");
            Method getImage = item.getClass().getMethod("getImageSrc");

            String name = (String) getName.invoke(item);
            String imageUrl = (String) getImage.invoke(item);

            ImageView imageView = new ImageView();
            imageView.setFitWidth(TILE_SIZE);
            imageView.setFitHeight(TILE_SIZE);
            imageView.getStyleClass().add("image-view");

            String cachedPath = IMAGE_CACHE_DIR + "/" + name.replaceAll("[^a-zA-Z0-9]", "_") + ".png";
            File imageFile = new File(cachedPath);

            if (!imageFile.exists()) {
                try (InputStream in = new URL(imageUrl).openStream(); FileOutputStream out = new FileOutputStream(imageFile)) {
                    in.transferTo(out);
                }
            }
            imageView.setImage(new Image(imageFile.toURI().toString()));

            VBox vbox = new VBox(5, imageView);
            vbox.getStyleClass().add("vbox");
            vbox.setPadding(new Insets(5));
            vbox.setPrefWidth(TILE_SIZE);
            vbox.setMaxWidth(TILE_SIZE);
            vbox.setMinWidth(TILE_SIZE);
            vbox.setOnMouseClicked((MouseEvent event) -> showDetail(item, imageFile.toURI().toString()));

            return vbox;
        } catch (Exception e) {
            e.printStackTrace();
            return new VBox();
        }
    }

    private void showDetail(Object item, String imagePath) {
        detailImage.setImage(new Image(imagePath));
        try {
            Method getName = item.getClass().getMethod("getName");
            String name = (String) getName.invoke(item);
            detailName.setText(name);

            StringBuilder statsBuilder = new StringBuilder("Stats:\n");
            for (Method method : item.getClass().getMethods()) {
                if ((method.getName().startsWith("get") || method.getName().startsWith("is")) && method.getParameterCount() == 0
                        && !method.getName().equals("getClass") && !method.getName().equals("getName") && !method.getName().equals("getImageSrc")) {
                    try {
                        Object value = method.invoke(item);
                        statsBuilder.append(method.getName().replaceFirst("get|is", ""))
                                .append(": ").append(value).append("\n");
                    } catch (Exception ignored) {}
                }
            }
            detailStats.setText(statsBuilder.toString());
        } catch (Exception e) {
            detailName.setText("Unknown Item");
            detailStats.setText("No stats available.");
        }
    }

    private <T> ScrollPane createTilePane(List<T> items) {
        TilePane tile = new TilePane(TILE_HGAP, TILE_VGAP);
        tile.setPadding(new Insets(TILE_PADDING));
        tile.setPrefColumns(TILE_COLUMNS);
        tile.setPrefTileWidth(TILE_SIZE);
        tile.setPrefTileHeight(TILE_SIZE);
        tile.setMaxWidth((TILE_SIZE + 20 + TILE_HGAP) * TILE_COLUMNS);

        for (T item : items) {
            tile.getChildren().add(createItemBox(item));
        }

        ScrollPane scroll = new ScrollPane(tile);
        scroll.setFitToWidth(true);
        return scroll;
    }
}
