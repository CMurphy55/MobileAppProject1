package app.dev.equipment.console.views

import app.dev.equipment.console.models.EquipmentJSONStore
import app.dev.equipment.console.models.EquipmentModel


class EquipmentView {

    fun menu() : Int {

        var option : Int
        var input: String?

        println("MAIN MENU")
        println(" 1. Add Equipment")
        println(" 2. Update Equipment")
        println(" 3. List All Equipments")
        println(" 4. Search Equipments")
        println(" 5. Delete Equipment")
        println("-1. Exit")
        println()
        print("Enter Option : ")
        input = readLine()!!
        option = if (input.toIntOrNull() != null && !input.isEmpty())
            input.toInt()
        else
            -9
        return option
    }

    fun listEquipments(equipments : EquipmentJSONStore) {
        println("List All Equipments")
        println()
        equipments.logAll()
        println()
    }

    fun showEquipment(equipment : EquipmentModel) {
        if(equipment != null)
            println("Equipment Details [ $equipment ]")
        else
            println("Equipment Not Found...")
    }

    fun addEquipmentData(equipment : EquipmentModel) : Boolean {

        println()
        print("Enter a Title : ")
        equipment.title = readLine()!!
        print("Enter a Description : ")
        equipment.description = readLine()!!

        return equipment.title.isNotEmpty() && equipment.description.isNotEmpty()
    }

    fun updateEquipmentData(equipment : EquipmentModel) : Boolean {

        var tempTitle: String?
        var tempDescription: String?

        if (equipment != null) {
            print("Enter a new Title for [ " + equipment.title + " ] : ")
            tempTitle = readLine()!!
            print("Enter a new Description for [ " + equipment.description + " ] : ")
            tempDescription = readLine()!!

            if (!tempTitle.isNullOrEmpty() && !tempDescription.isNullOrEmpty()) {
                equipment.title = tempTitle
                equipment.description = tempDescription
                return true
            }
        }
        return false
    }

    fun getId() : Long {
        var strId : String? // String to hold user input
        var searchId : Long // Long to hold converted id
        print("Enter id to Search/Update/Delete : ")
        strId = readLine()!!
        searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
            strId.toLong()
        else
            -9

        return searchId
    }
}
