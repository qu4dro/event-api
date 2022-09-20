package orlov.utils

import orlov.data.models.EventDTO

fun EventDTO.validate(): Boolean {
    if (name.isNullOrEmpty() || name.length > 100) return false
    if (description.isNullOrEmpty() || description.length > 255) return false
    if (creator.isNullOrEmpty()) return false
    if (lat.isNullOrEmpty() || lat.length > 16) return false
    if (lng.isNullOrEmpty() || lng.length > 16) return false
    return true
}