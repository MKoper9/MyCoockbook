package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.Ingredient;
import model.Recipe;
import model.enums.Level;
import model.enums.Meal;
import model.enums.Type;
import service.CookbookService;
import service.WindowService;
import utility.InMemoryDB;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class CookbookController {
    @FXML
    public ComboBox<Recipe> cb_recipe;
    @FXML
    public TextArea ta_description;

    @FXML
    private TextField tf_type;
    @FXML
    private TextField tf_level;
    @FXML
    private TextField tf_meal;
    @FXML
    private TextField tf_prepatetime;
    @FXML
    private ListView<Ingredient> lv_listview;
    @FXML
    private ImageView img_image;
    @FXML
    private TextField tf_title;

    //nowe

    @FXML
    private TableView<Recipe> tbl_recipes;
    @FXML
    private TableColumn<Recipe,String> c_title;
    @FXML
    private TableColumn<Recipe,String> c_description;
    @FXML
    private TableColumn<Recipe, Integer> c_time;
    @FXML
    private TableColumn<Recipe, Meal> c_meal;
    @FXML
    private TableColumn<Recipe, Level> c_level;
    @FXML
    private TableColumn<Recipe, Type> c_type;
    @FXML
    private TableColumn<Recipe, String> c_ingredients;
    @FXML
    private TextField e_title;
    @FXML
    private TextArea e_description;
    @FXML
    private Spinner<Integer> e_time;
    @FXML
    private ComboBox<Type> e_type;
    @FXML
    public ComboBox e_meal;
    @FXML
    private ComboBox<Level> e_level;
    @FXML
    private Button e_image;
    @FXML
    private ComboBox<Ingredient> e_ingredient;
    @FXML
    private ImageView e_view;
    @FXML
    private Button btn_save;
    @FXML
    private Button btn_delete;
    @FXML
    public ListView<Ingredient> lv_ingredientsView;

    // obiekty globalne
    public static ObservableList<Recipe> recipes_fx = FXCollections.observableArrayList();
    public static ObservableList<Ingredient> ingredients_fx = FXCollections.observableArrayList();
    private WindowService windowService;
    private CookbookService cookbookService;
    private Recipe recipeToDelete;
    private String imagePath;


    public void initialize() {
        windowService = new WindowService();
        cookbookService = new CookbookService();
        // wprowadź obiekty z listy recipes do recipes_fx
        recipes_fx.addAll(InMemoryDB.recipes);
        // wprowadzenie tytułów do combo
        cb_recipe.setItems(recipes_fx);
        //przypisać pola obiektu Recipe do kolumn tabeli
        cookbookService.setTableProperty(c_title, c_description,c_time, c_meal, c_level, c_type, c_ingredients);
        //Wprowadzenie danych do tabeli
        cookbookService.setTableItems(tbl_recipes,recipes_fx);
        //wprowadzenie danych do combo
        cookbookService.setMealItems(e_meal);
        cookbookService.setTypeItems(e_type);
        cookbookService.setLevelItems(e_level);

        //inicjalizacja spinnera
        // konfiguracja spinera
        e_time.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 180, 0,5));

        cookbookService.setIngredientsCombo(e_ingredient, (ArrayList<Ingredient>) InMemoryDB.ingredients);


    }
    @FXML
    void logoutAction(ActionEvent event)throws IOException {
        windowService.openNewWindow("/view/loginView.fxml", "Panel Logowania");
        windowService.closeCurrentWindow(tf_title);

    }
    @FXML
    void getReceiptaction(ActionEvent event) {

        Recipe selectedRecipe = cb_recipe.getValue();
        cookbookService.setSelectedRecipe(selectedRecipe, tf_title,ta_description,tf_type,
                tf_level,tf_meal,  tf_prepatetime,lv_listview, img_image);
    }

    @FXML
    void selectRowAction(MouseEvent event) {
        recipeToDelete = cookbookService.getRecipeFromSelectedRow(tbl_recipes);
        // aktywujemy przycisk
        btn_delete.setDisable(false);

    }

    @FXML
    void deleteReciepAction(ActionEvent event) {
        cookbookService.deleteRecipe(recipeToDelete, recipes_fx);
        // odświeżenie tabelki
        cookbookService.setTableItems(tbl_recipes, recipes_fx);
        btn_delete.setDisable(true);
    }
    @FXML
    void saveReciep(ActionEvent event) {

        cookbookService.saveRecipe(e_title,e_description,e_time,e_meal,e_level,e_type,
                imagePath, tbl_recipes, lv_ingredientsView, e_ingredient,e_view);

    }
    @FXML
    void addIngredientAction(ActionEvent event) {

        //cookbookService.updateIngredientsCombo(e_ingredients, e_ingredients.getValue());
        ObservableList<Ingredient> ingredients = lv_ingredientsView.getItems();
        ingredients.add(e_ingredient.getValue());
        lv_ingredientsView.setItems(ingredients);

    }
    @FXML
    void aploadImageAction(ActionEvent event) {
        imagePath = cookbookService.uploadFile(e_view);
    }



}

