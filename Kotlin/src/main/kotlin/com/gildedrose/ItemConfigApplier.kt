package com.gildedrose

/**
 * Matches [item] to its appropriate config so it can be updated.
 */
class ItemConfigApplier(private val item: Item) {
    private val itemConfig = ItemConfigs.findItemConfigFor(item)

    /**
     * Returns a copy of item that has been updated according to
     * the itemConfig.
     */
    fun updatedItem(): Item =
        Item(item.name, item.sellIn, item.quality).apply {
            sellIn = itemConfig.updateSellIn(this)
            quality = itemConfig.updateQuality(this)
        }
}
