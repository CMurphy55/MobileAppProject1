package app.dev.equipment.console.models

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class EquipmentMemStore : EquipmentStore {

    val equipments = ArrayList<EquipmentModel>()

    override fun findAll(): List<EquipmentModel> {
        return equipments
    }

    override fun findOne(id: Long) : EquipmentModel? {
        var foundEquipment: EquipmentModel? = equipments.find { p -> p.id == id }
        return foundEquipment
    }

    override fun create(equipment: EquipmentModel) {
        equipment.id = getId()
        equipments.add(equipment)
        logAll()
    }
    override fun delete(equipment: EquipmentModel) {
        equipments.remove(equipment)
    }

    override fun update(equipment: EquipmentModel) {
        var foundEquipment = findOne(equipment.id)
        if (foundEquipment != null) {
            foundEquipment.title = equipment.title
            foundEquipment.description = equipment.description
        }
    }

    internal fun logAll() {
        equipments.forEach { logger.info("${it}") }
    }
}
