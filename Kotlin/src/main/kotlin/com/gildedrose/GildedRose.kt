package com.gildedrose

class GildedRose(var items: Array<Item>) {

    fun updateQuality() {
        items = items.map(::ItemConfigApplier)
            .map(ItemConfigApplier::updatedItem)
            .toTypedArray()
    }
}
