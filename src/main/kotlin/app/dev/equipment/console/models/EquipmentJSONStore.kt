package app.dev.equipment.console.models

import mu.KotlinLogging
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import app.dev.equipment.console.helpers.*
import java.util.*



private val logger = KotlinLogging.logger {}




val JSON_FILE = "equipments.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<EquipmentModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class EquipmentJSONStore : EquipmentStore {

    var equipments = mutableListOf<EquipmentModel>()

    init {
        if (exists(JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<EquipmentModel> {
        return equipments
    }

    override fun findOne(id: Long) : EquipmentModel? {
        var foundEquipment: EquipmentModel? = equipments.find { p -> p.id == id }
        return foundEquipment
    }

    override fun create(equipment: EquipmentModel) {
        equipment.id = generateRandomId()
        equipments.add(equipment)
        serialize()
    }

    override fun update(equipment: EquipmentModel) {
        var foundEquipment = findOne(equipment.id!!)
        if (foundEquipment != null) {
            foundEquipment.title = equipment.title
            foundEquipment.description = equipment.description
        }
        serialize()
    }

    override fun delete (equipment: EquipmentModel) {
        equipments.remove(equipment)
        serialize()
    }

    internal fun logAll() {
        equipments.forEach { logger.info("${it}") }
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(equipments, listType)
        write(JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(JSON_FILE)
        equipments = Gson().fromJson(jsonString, listType)
    }
}
