package com.xlingmao;


import com.xlingmao.service.ScreenMonitorService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.log4j.Logger;
import javafx.scene.Parent;
import java.io.IOException;

/**
 * Created by dell on 2016/10/25.
 */
public class App extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private FlowPane flowPane;


    private static final Logger logger = Logger.getLogger(App.class);

    private static final String LAYOUT_ROOTLAYOUT= "/view/initView/RootLayout.fxml";

    public static void main(String args[]){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("猫巢微信运营系统");
        initRootLayout();
        initScrollPaneView();
    }

    private Node findById(String id, Pane layout){
        try{
            return layout.lookup(id);
        }catch(Exception e){
            logger.error(e);
        }
        return null;

    }

    /**
     * Initializes the root layout.
     */
    private void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("/view/initView/RootLayout.fxml"));
            rootLayout = loader.load();
            Scene scene = new Scene(rootLayout);
            scene.getStylesheets().add(getClass().getResource("/view/css/device.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setMaximized(true);
            primaryStage.show();

        } catch (IOException e) {
            logger.error("=====" + e);
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    private void initScrollPaneView() {

        flowPane = (FlowPane) findById("#flowPane",rootLayout);

        ScreenMonitorService smService = new ScreenMonitorService();
        smService.setFlowPane(flowPane);
        smService.setPeriod(Duration.seconds(2));
        smService.start();
    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
