package ui;

import Entitys.Origin;
import Entitys.Personagem;
import Items.Armor.ChestArmor;
import Items.Armor.HandArmor;
import Items.Armor.HeadArmor;
import Items.Armor.LegArmor;
import Items.Rune.Rune;
import Items.Weapon.LHandWeapon;
import Items.Weapon.RHandWeapon;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
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
    private VBox EquipBox;

    private VBox RightStats;

    private Personagem hunter;

    private int RuneIndex;

    public InventoryPane(Repository repo, Origin origin, Runnable Continue) {

        hunter = new Personagem(origin, repo);

        StackPane rootStack = new StackPane();

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
        tabBox.getStyleClass().add("tab-box");
        detailImage = new ImageView();
        detailImage.setFitWidth(250);
        detailImage.setFitHeight(250);

        detailName = new Text("Select an item to view details");
        detailStats = new Text("");

        EquipBox = new VBox(20);
        EquipBox.setAlignment(Pos.CENTER);

        RightStats = new VBox(5);
        RightStats.setPadding(new Insets(10));

        RefreshEquipmentBox();

        VBox leftStats = new VBox(5);
        leftStats.setPadding(new Insets(10));
        leftStats.getChildren().addAll(
                new Text("Level: " + hunter.getLevel()),
                new Text("Insight: " + hunter.getInsight()),
                new Text("Vitality: " + hunter.getVitality()),
                new Text("Endurance: " + hunter.getEndurence()),
                new Text("Strength: " + hunter.getStrength()),
                new Text("Skill: " + hunter.getSkill()),
                new Text("BloodTinge: " + hunter.getBloodtinge()),
                new Text("Arcane: " + hunter.getArcane())
        );

        HBox statsBox = new HBox(20, leftStats, RightStats);
        HBox rightPane = new HBox(5, EquipBox, statsBox);
        statsBox.getStyleClass().add("detail-stats");

        detailPane = new VBox(10, detailImage, detailName, detailStats);
        detailPane.setPadding(new Insets(20));
        detailPane.setPrefWidth(450);
        detailName.getStyleClass().add("detail-title");
        detailStats.getStyleClass().add("detail-stats");
        detailPane.getStyleClass().add("detail-stats");

        Button continueButton = new Button("Continue");
        continueButton.setOnAction(e -> {
            Continue.run();
        });

        HBox splitPane = new HBox(tabBox, detailPane, rightPane, continueButton);
        root.setCenter(splitPane);

        root.getStyleClass().add("root-inventory");
        root.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
    }

    private void RefreshEquipmentBox(){
        EquipBox.getChildren().clear();

        String RHhandPath = IMAGE_CACHE_DIR + "/" + hunter.getRHand().getName().replaceAll("[^a-zA-Z0-9]", "_") + ".png";
        EquipBox.getChildren().add(new ImageView(new Image(RHhandPath, 100, 100, true, true)));

        String LHandPath = IMAGE_CACHE_DIR + "/" + hunter.getLHand().getName().replaceAll("[^a-zA-Z0-9]", "_") + ".png";
        EquipBox.getChildren().add(new ImageView(new Image(LHandPath, 100, 100, true, true)));

        String HeadPath = IMAGE_CACHE_DIR + "/" + hunter.getHead().getName().replaceAll("[^a-zA-Z0-9]", "_") + ".png";
        EquipBox.getChildren().add(new ImageView(new Image(HeadPath, 100, 100, true, true)));

        String ChestPath = IMAGE_CACHE_DIR + "/" + hunter.getChest().getName().replaceAll("[^a-zA-Z0-9]", "_") + ".png";
        EquipBox.getChildren().add(new ImageView(new Image(ChestPath, 100, 100, true, true)));

        String HandPath = IMAGE_CACHE_DIR + "/" + hunter.getHands().getName().replaceAll("[^a-zA-Z0-9]", "_") + ".png";
        EquipBox.getChildren().add(new ImageView(new Image(HandPath, 100, 100, true, true)));

        String LegPath = IMAGE_CACHE_DIR + "/" + hunter.getLegs().getName().replaceAll("[^a-zA-Z0-9]", "_") + ".png";
        EquipBox.getChildren().add(new ImageView(new Image(LegPath, 100, 100, true, true)));

        String Rune1Path = IMAGE_CACHE_DIR + "/" + hunter.getRuna1().getName().replaceAll("[^a-zA-Z0-9]", "_") + ".png";
        EquipBox.getChildren().add(new ImageView(new Image(Rune1Path, 100, 100, true, true)));

        String Rune2Path = IMAGE_CACHE_DIR + "/" + hunter.getRuna2().getName().replaceAll("[^a-zA-Z0-9]", "_") + ".png";
        EquipBox.getChildren().add(new ImageView(new Image(Rune2Path, 100, 100, true, true)));

        String Rune3Path = IMAGE_CACHE_DIR + "/" + hunter.getRuna3().getName().replaceAll("[^a-zA-Z0-9]", "_") + ".png";
        EquipBox.getChildren().add(new ImageView(new Image(Rune3Path, 100, 100, true, true)));

        RightStats.getChildren().clear();

        HBox hpBox = createStatRow("HP: ", String.valueOf(hunter.getHealth()), "hp-value");

        // Stamina
        HBox staminaBox = createStatRow("Stamina: ", String.valueOf(hunter.getStamina()), "stamina-value");

        // Discovery
        HBox discoveryBox = createStatRow("Discovery: ", String.valueOf(hunter.getDiscovery()), "secondary-value");

        // Defense
        HBox defenseBox = createStatRow("Def: ", String.valueOf(hunter.getDefense()), "defense-value");

        // Resistances
        HBox slowPoisonBox = createStatRow("Slow Poison Resist: ", String.valueOf(hunter.getSlowPoisonResist()), "resistance-value");
        HBox rapidPoisonBox = createStatRow("Rapid Poison Resist: ", String.valueOf(hunter.getRapidPoisonResist()), "resistance-value");
        HBox frenzyBox = createStatRow("Frenzy Resist: ", String.valueOf(hunter.getFrenzyResist()), "resistance-value");

        // Special
        HBox beastHoodBox = createStatRow("BeastHood: ", String.valueOf(hunter.getBestHood()), "special-value");

        // Damage Reductions
        HBox physicalBox = createStatRow("Physical Dmg Reduction: ", String.valueOf(hunter.getPhysicalDmgReduction()), "reduction-value");
        HBox bluntBox = createStatRow("VS. Blunt: ", String.valueOf(hunter.getBluntDmgReduction()), "reduction-value");
        HBox thrustBox = createStatRow("VS. Thrust: ", String.valueOf(hunter.getThrustDmgReduction()), "reduction-value");
        HBox bloodBox = createStatRow("Blood Dmg Reduction: ", String.valueOf(hunter.getBloodDmgReduction()), "reduction-value");
        HBox arcaneBox = createStatRow("Arcane Dmg Reduction: ", String.valueOf(hunter.getArcaneDmgReduction()), "reduction-value");
        HBox fireBox = createStatRow("Fire Dmg Reduction: ", String.valueOf(hunter.getFireDmgReduction()), "reduction-value");
        HBox boltBox = createStatRow("Bolt Dmg Reduction: ", String.valueOf(hunter.getBoltDmgReduction()), "reduction-value");

        RightStats.getChildren().addAll(
                hpBox,
                staminaBox,
                discoveryBox,
                defenseBox,
                slowPoisonBox,
                rapidPoisonBox,
                frenzyBox,
                beastHoodBox,
                physicalBox,
                bluntBox,
                thrustBox,
                bloodBox,
                arcaneBox,
                fireBox,
                boltBox
        );
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
            vbox.setOnMouseClicked(e -> {
                showDetail(item, imageFile.toURI().toString());
                handleSelection(item);
            });

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


    private HBox createStatRow(String labelText, String valueText, String valueStyleClass) {
        Text label = new Text(labelText);
        label.getStyleClass().add("inventory-label");

        Text value = new Text(valueText);
        value.getStyleClass().add("numbers-especial-inventory");
        value.getStyleClass().add(valueStyleClass);

        HBox row = new HBox(5, label, value);
        row.getStyleClass().add("inventory-label");
        row.setAlignment(Pos.CENTER_RIGHT);

        return row;
    }

    private void handleSelection(Object item) {
        if(item instanceof RHandWeapon){
            hunter.setRHand((RHandWeapon) item);
        } else if (item instanceof LHandWeapon){
            hunter.setLHand((LHandWeapon) item);
        } else if (item instanceof HeadArmor){
            hunter.setHeadArmor((HeadArmor) item);
        } else if (item instanceof ChestArmor){
            hunter.setChestArmor((ChestArmor) item);
        } else if (item instanceof LegArmor){
            hunter.setLegArmor((LegArmor) item);
        } else if (item instanceof HandArmor){
            hunter.setHandArmor((HandArmor) item);
        } else if (item instanceof Rune) {
            RuneIndex = (RuneIndex + 1) % 3;
            hunter.setRuna((Rune) item, RuneIndex);
        }



        RefreshEquipmentBox();
    }

    public Personagem getHunter() { return hunter; }

    private <T> ScrollPane createTilePane(List<T> items) {
        TilePane tile = new TilePane(TILE_HGAP, TILE_VGAP);
        tile.setPadding(new Insets(TILE_PADDING));
        tile.setPrefColumns(TILE_COLUMNS);
        tile.setPrefTileWidth(TILE_SIZE);
        tile.setPrefTileHeight(TILE_SIZE);
        tile.setMaxWidth((TILE_SIZE + 20 + TILE_HGAP) * TILE_COLUMNS);


        for (T item : items) {
            VBox box = createItemBox(item);
            tile.getChildren().add(box);
        }

        ScrollPane scroll = new ScrollPane(tile);
        scroll.setFitToWidth(true);
        return scroll;
    }

}
