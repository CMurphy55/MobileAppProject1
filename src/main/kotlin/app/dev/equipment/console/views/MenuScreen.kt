package app.dev.equipment.console.views

import app.dev.equipment.console.controllers.EquipmentUIController
import tornadofx.*
import javafx.application.Platform
import javafx.geometry.Orientation

class MenuScreen : View("Equipment Main Menu") {

    val equipmentUIController: EquipmentUIController by inject()

    override val root = form {
        setPrefSize(400.0, 200.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            text("")
            button("Add Equipment") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        equipmentUIController.loadAddScreen()
                    }
                }
            }
            text("")
            button("View Your Equipment") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        equipmentUIController.loadListScreen()
                    }

                }
            }

            text("")
            button("Exit") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        Platform.exit();
                        System.exit(0);
                    }
                }
            }
        }

    }


}
