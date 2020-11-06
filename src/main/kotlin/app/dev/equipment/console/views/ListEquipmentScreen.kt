package app.dev.equipment.console.views

import app.dev.equipment.console.controllers.EquipmentUIController
import app.dev.equipment.console.models.EquipmentModel
import tornadofx.*

class ListEquipmentScreen : View("List Equipments") {

    val equipmentUIController: EquipmentUIController by inject()
    val tableContent = equipmentUIController.equipments.findAll()
    val data = tableContent.observable()


    override val root = vbox {
        setPrefSize(600.0, 200.0)
        tableview(data) {
            readonlyColumn("ID", EquipmentModel::id)
            readonlyColumn("TITLE", EquipmentModel::title)
            readonlyColumn("DESCRIPTION", EquipmentModel::description)
        }
        button("Close") {
            useMaxWidth = true
            action {
                runAsyncWithProgress {
                    equipmentUIController.closeList()
                }
            }
        }
    }

}
