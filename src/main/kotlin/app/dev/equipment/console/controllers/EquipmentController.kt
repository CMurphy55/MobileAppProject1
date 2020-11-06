package app.dev.equipment.console.controllers

import mu.KotlinLogging
import app.dev.equipment.console.models.EquipmentModel
import app.dev.equipment.console.models.EquipmentJSONStore
import app.dev.equipment.console.views.EquipmentView


class EquipmentController {

    // val equipments = EquipmentMemStore()
    val equipments = EquipmentJSONStore()
    val equipmentView = EquipmentView()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching Equipment Console App" }
        println("Equipment Kotlin App Version 4.0")
    }

    fun start() {
        var input: Int

        do {
            input = menu()
            when (input) {
                1 -> add()
                2 -> update()
                3 -> list()
                4 -> search()
                5 -> delete()
                -99 -> dummyData()
                -1 -> println("Exiting App")
                else -> println("Invalid Option")
            }
            println()
        } while (input != -1)
        logger.info { "Shutting Down Equipment Console App" }
    }

    fun menu() :Int { return equipmentView.menu() }

    fun add(){
        var aEquipment = EquipmentModel()

        if (equipmentView.addEquipmentData(aEquipment))
            equipments.create(aEquipment)
        else
            logger.info("Equipment Not Added")
    }

    fun list() {
        equipmentView.listEquipments(equipments)
    }

    fun update() {

        equipmentView.listEquipments(equipments)
        var searchId = equipmentView.getId()
        val aEquipment = search(searchId)

        if(aEquipment != null) {
            if(equipmentView.updateEquipmentData(aEquipment)) {
                equipments.update(aEquipment)
                equipmentView.showEquipment(aEquipment)
                logger.info("Equipment Updated : [ $aEquipment ]")
            }
            else
                logger.info("Equipment Not Updated")
        }
        else
            println("Equipment Not Updated...")
    }

    fun delete() {
        equipmentView.listEquipments(equipments)
        var searchId = equipmentView.getId()
        val aEquipment = search(searchId)

        if(aEquipment != null) {
            equipments.delete(aEquipment)
            println("Equipment Deleted...")
            equipmentView.listEquipments(equipments)
        }
        else
            println("Equipment Not Deleted...")
    }

    fun search() {
        val aEquipment = search(equipmentView.getId())!!
        equipmentView.showEquipment(aEquipment)
    }


    fun search(id: Long) : EquipmentModel? {
        var foundEquipment = equipments.findOne(id)
        return foundEquipment
    }

    fun dummyData() {
        equipments.create(EquipmentModel(title = "New York New York", description = "So Good They Named It Twice"))
        equipments.create(EquipmentModel(title= "Ring of Kerry", description = "Some place in the Kingdom"))
        equipments.create(EquipmentModel(title = "Waterford City", description = "You get great Blaas Here!!"))
    }
}
