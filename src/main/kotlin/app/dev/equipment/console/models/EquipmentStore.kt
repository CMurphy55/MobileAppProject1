package app.dev.equipment.console.models

interface EquipmentStore {
    fun findAll(): List<EquipmentModel>
    fun findOne(id: Long): EquipmentModel?
    fun create(placemark: EquipmentModel)
    fun update(placemark: EquipmentModel)
    fun delete(placemark: EquipmentModel)
}