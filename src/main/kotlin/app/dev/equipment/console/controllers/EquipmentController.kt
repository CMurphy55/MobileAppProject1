package app.dev.equipment.console.controllers

import mu.KotlinLogging
import app.dev.equipment.console.models.EquipmentModel
import app.dev.equipment.console.models.EquipmentJSONStore
import app.dev.equipment.console.views.EquipmentView
import com.github.mm.coloredconsole.colored //references the 'ColoredConsole.kt' console file so that I can add colours to text


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
                6 -> dummyData() //dummy data function press 6

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
        equipments.create(EquipmentModel(title = "Rucksack", description = "Stores essential items"))
        equipments.create(EquipmentModel(title= "Swiss Army Knife", description = "Multi-purpose tool"))
        equipments.create(EquipmentModel(title = "Water bottle", description = "Holds 750ml of liquid"))
        colored {
            println("Sample data has been added".cyan.italic) //I wrapped this print statement in a coloured function which coloured whatever is in this print line statement cyan with italic formatting.
        }
    }


}
