package app.dev.equipment.console.controllers

import mu.KotlinLogging
import app.dev.equipment.console.models.EquipmentJSONStore
import app.dev.equipment.console.models.EquipmentModel
import app.dev.equipment.console.views.AddEquipmentScreen
import app.dev.equipment.console.views.ListEquipmentScreen
import app.dev.equipment.console.views.MenuScreen
import tornadofx.Controller
import tornadofx.runLater

class EquipmentUIController : Controller() {



    val equipments = EquipmentJSONStore()
    private val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching Equipment TornadoFX UI App" }
    }
    fun add(_title : String, _description : String){

        var aEquipment = EquipmentModel(title = _title, description = _description)
        equipments.create(aEquipment)
        logger.info("Equipment Added")
    }

    fun loadListScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(ListEquipmentScreen::class, sizeToScene = true, centerOnScreen = true)
        }
        equipments.logAll()
    }

    fun loadAddScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(AddEquipmentScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

    fun closeAdd() {
        runLater {
            find(AddEquipmentScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }
    fun closeList() {
        runLater {
            find(ListEquipmentScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

}
