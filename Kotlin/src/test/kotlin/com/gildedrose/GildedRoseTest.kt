package com.gildedrose

import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.TypeSafeMatcher
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class GildedRoseTest {

    fun run(times: Int): Array<Item> =
        GildedRose(createItems()).apply {
            for (i in 1..times) {
                updateQuality()
            }
        }.items

    fun print() {
        for (i in 0 until 100) {
            val items = run(i)
            println(
                """
                Arguments.of($i, arrayOf(
                    ${items.joinToString(",\n") { item -> """Item("${item.name}", ${item.sellIn}, ${item.quality})""" }}
                )),
            """
            )
        }
    }

    @ParameterizedTest
    @MethodSource("expectedItems")
    fun testDay(days: Int, expectedItems: Array<Item>) {
        val items = run(days)
        expectedItems.forEachIndexed { index, expectedItem ->
            assertThat(items[index], contains(expectedItem))
        }
    }

    private fun contains(expectedItem: Item): Matcher<in Item> =
        object : TypeSafeMatcher<Item>() {
            override fun describeTo(description: Description) {
                description.appendValue(expectedItem)
            }

            override fun matchesSafely(item: Item): Boolean =
                item.name == expectedItem.name && item.sellIn == expectedItem.sellIn && item.quality == expectedItem.quality
        }

    companion object {

        @JvmStatic
        private fun expectedItems(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    0, arrayOf(
                        Item("+5 Dexterity Vest", 10, 20),
                        Item("Aged Brie", 2, 0),
                        Item("Elixir of the Mongoose", 5, 7),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                        Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
                        Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
                        Item("Conjured Mana Cake", 3, 6)
                    )
                ),
                Arguments.of(
                    1, arrayOf(
                        Item("+5 Dexterity Vest", 9, 19),
                        Item("Aged Brie", 1, 1),
                        Item("Elixir of the Mongoose", 4, 6),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", 14, 21),
                        Item("Backstage passes to a TAFKAL80ETC concert", 9, 50),
                        Item("Backstage passes to a TAFKAL80ETC concert", 4, 50),
                        Item("Conjured Mana Cake", 2, 5)
                    )
                ),
                Arguments.of(
                    2, arrayOf(
                        Item("+5 Dexterity Vest", 8, 18),
                        Item("Aged Brie", 0, 2),
                        Item("Elixir of the Mongoose", 3, 5),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", 13, 22),
                        Item("Backstage passes to a TAFKAL80ETC concert", 8, 50),
                        Item("Backstage passes to a TAFKAL80ETC concert", 3, 50),
                        Item("Conjured Mana Cake", 1, 4)
                    )
                ),
                Arguments.of(
                    3, arrayOf(
                        Item("+5 Dexterity Vest", 7, 17),
                        Item("Aged Brie", -1, 4),
                        Item("Elixir of the Mongoose", 2, 4),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", 12, 23),
                        Item("Backstage passes to a TAFKAL80ETC concert", 7, 50),
                        Item("Backstage passes to a TAFKAL80ETC concert", 2, 50),
                        Item("Conjured Mana Cake", 0, 3)
                    )
                ),
                Arguments.of(
                    4, arrayOf(
                        Item("+5 Dexterity Vest", 6, 16),
                        Item("Aged Brie", -2, 6),
                        Item("Elixir of the Mongoose", 1, 3),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", 11, 24),
                        Item("Backstage passes to a TAFKAL80ETC concert", 6, 50),
                        Item("Backstage passes to a TAFKAL80ETC concert", 1, 50),
                        Item("Conjured Mana Cake", -1, 1)
                    )
                ),
                Arguments.of(
                    5, arrayOf(
                        Item("+5 Dexterity Vest", 5, 15),
                        Item("Aged Brie", -3, 8),
                        Item("Elixir of the Mongoose", 0, 2),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", 10, 25),
                        Item("Backstage passes to a TAFKAL80ETC concert", 5, 50),
                        Item("Backstage passes to a TAFKAL80ETC concert", 0, 50),
                        Item("Conjured Mana Cake", -2, 0)
                    )
                ),
                Arguments.of(
                    6, arrayOf(
                        Item("+5 Dexterity Vest", 4, 14),
                        Item("Aged Brie", -4, 10),
                        Item("Elixir of the Mongoose", -1, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", 9, 27),
                        Item("Backstage passes to a TAFKAL80ETC concert", 4, 50),
                        Item("Backstage passes to a TAFKAL80ETC concert", -1, 0),
                        Item("Conjured Mana Cake", -3, 0)
                    )
                ),
                Arguments.of(
                    7, arrayOf(
                        Item("+5 Dexterity Vest", 3, 13),
                        Item("Aged Brie", -5, 12),
                        Item("Elixir of the Mongoose", -2, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", 8, 29),
                        Item("Backstage passes to a TAFKAL80ETC concert", 3, 50),
                        Item("Backstage passes to a TAFKAL80ETC concert", -2, 0),
                        Item("Conjured Mana Cake", -4, 0)
                    )
                ),
                Arguments.of(
                    8, arrayOf(
                        Item("+5 Dexterity Vest", 2, 12),
                        Item("Aged Brie", -6, 14),
                        Item("Elixir of the Mongoose", -3, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", 7, 31),
                        Item("Backstage passes to a TAFKAL80ETC concert", 2, 50),
                        Item("Backstage passes to a TAFKAL80ETC concert", -3, 0),
                        Item("Conjured Mana Cake", -5, 0)
                    )
                ),
                Arguments.of(
                    9, arrayOf(
                        Item("+5 Dexterity Vest", 1, 11),
                        Item("Aged Brie", -7, 16),
                        Item("Elixir of the Mongoose", -4, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", 6, 33),
                        Item("Backstage passes to a TAFKAL80ETC concert", 1, 50),
                        Item("Backstage passes to a TAFKAL80ETC concert", -4, 0),
                        Item("Conjured Mana Cake", -6, 0)
                    )
                ),
                Arguments.of(
                    10, arrayOf(
                        Item("+5 Dexterity Vest", 0, 10),
                        Item("Aged Brie", -8, 18),
                        Item("Elixir of the Mongoose", -5, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", 5, 35),
                        Item("Backstage passes to a TAFKAL80ETC concert", 0, 50),
                        Item("Backstage passes to a TAFKAL80ETC concert", -5, 0),
                        Item("Conjured Mana Cake", -7, 0)
                    )
                ),
                Arguments.of(
                    11, arrayOf(
                        Item("+5 Dexterity Vest", -1, 8),
                        Item("Aged Brie", -9, 20),
                        Item("Elixir of the Mongoose", -6, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", 4, 38),
                        Item("Backstage passes to a TAFKAL80ETC concert", -1, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -6, 0),
                        Item("Conjured Mana Cake", -8, 0)
                    )
                ),
                Arguments.of(
                    12, arrayOf(
                        Item("+5 Dexterity Vest", -2, 6),
                        Item("Aged Brie", -10, 22),
                        Item("Elixir of the Mongoose", -7, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", 3, 41),
                        Item("Backstage passes to a TAFKAL80ETC concert", -2, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -7, 0),
                        Item("Conjured Mana Cake", -9, 0)
                    )
                ),
                Arguments.of(
                    13, arrayOf(
                        Item("+5 Dexterity Vest", -3, 4),
                        Item("Aged Brie", -11, 24),
                        Item("Elixir of the Mongoose", -8, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", 2, 44),
                        Item("Backstage passes to a TAFKAL80ETC concert", -3, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -8, 0),
                        Item("Conjured Mana Cake", -10, 0)
                    )
                ),
                Arguments.of(
                    14, arrayOf(
                        Item("+5 Dexterity Vest", -4, 2),
                        Item("Aged Brie", -12, 26),
                        Item("Elixir of the Mongoose", -9, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", 1, 47),
                        Item("Backstage passes to a TAFKAL80ETC concert", -4, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -9, 0),
                        Item("Conjured Mana Cake", -11, 0)
                    )
                ),
                Arguments.of(
                    15, arrayOf(
                        Item("+5 Dexterity Vest", -5, 0),
                        Item("Aged Brie", -13, 28),
                        Item("Elixir of the Mongoose", -10, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", 0, 50),
                        Item("Backstage passes to a TAFKAL80ETC concert", -5, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -10, 0),
                        Item("Conjured Mana Cake", -12, 0)
                    )
                ),
                Arguments.of(
                    16, arrayOf(
                        Item("+5 Dexterity Vest", -6, 0),
                        Item("Aged Brie", -14, 30),
                        Item("Elixir of the Mongoose", -11, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -1, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -6, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -11, 0),
                        Item("Conjured Mana Cake", -13, 0)
                    )
                ),
                Arguments.of(
                    17, arrayOf(
                        Item("+5 Dexterity Vest", -7, 0),
                        Item("Aged Brie", -15, 32),
                        Item("Elixir of the Mongoose", -12, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -2, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -7, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -12, 0),
                        Item("Conjured Mana Cake", -14, 0)
                    )
                ),
                Arguments.of(
                    18, arrayOf(
                        Item("+5 Dexterity Vest", -8, 0),
                        Item("Aged Brie", -16, 34),
                        Item("Elixir of the Mongoose", -13, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -3, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -8, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -13, 0),
                        Item("Conjured Mana Cake", -15, 0)
                    )
                ),
                Arguments.of(
                    19, arrayOf(
                        Item("+5 Dexterity Vest", -9, 0),
                        Item("Aged Brie", -17, 36),
                        Item("Elixir of the Mongoose", -14, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -4, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -9, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -14, 0),
                        Item("Conjured Mana Cake", -16, 0)
                    )
                ),
                Arguments.of(
                    20, arrayOf(
                        Item("+5 Dexterity Vest", -10, 0),
                        Item("Aged Brie", -18, 38),
                        Item("Elixir of the Mongoose", -15, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -5, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -10, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -15, 0),
                        Item("Conjured Mana Cake", -17, 0)
                    )
                ),
                Arguments.of(
                    21, arrayOf(
                        Item("+5 Dexterity Vest", -11, 0),
                        Item("Aged Brie", -19, 40),
                        Item("Elixir of the Mongoose", -16, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -6, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -11, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -16, 0),
                        Item("Conjured Mana Cake", -18, 0)
                    )
                ),
                Arguments.of(
                    22, arrayOf(
                        Item("+5 Dexterity Vest", -12, 0),
                        Item("Aged Brie", -20, 42),
                        Item("Elixir of the Mongoose", -17, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -7, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -12, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -17, 0),
                        Item("Conjured Mana Cake", -19, 0)
                    )
                ),
                Arguments.of(
                    23, arrayOf(
                        Item("+5 Dexterity Vest", -13, 0),
                        Item("Aged Brie", -21, 44),
                        Item("Elixir of the Mongoose", -18, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -8, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -13, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -18, 0),
                        Item("Conjured Mana Cake", -20, 0)
                    )
                ),
                Arguments.of(
                    24, arrayOf(
                        Item("+5 Dexterity Vest", -14, 0),
                        Item("Aged Brie", -22, 46),
                        Item("Elixir of the Mongoose", -19, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -9, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -14, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -19, 0),
                        Item("Conjured Mana Cake", -21, 0)
                    )
                ),
                Arguments.of(
                    25, arrayOf(
                        Item("+5 Dexterity Vest", -15, 0),
                        Item("Aged Brie", -23, 48),
                        Item("Elixir of the Mongoose", -20, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -10, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -15, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -20, 0),
                        Item("Conjured Mana Cake", -22, 0)
                    )
                ),
                Arguments.of(
                    26, arrayOf(
                        Item("+5 Dexterity Vest", -16, 0),
                        Item("Aged Brie", -24, 50),
                        Item("Elixir of the Mongoose", -21, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -11, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -16, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -21, 0),
                        Item("Conjured Mana Cake", -23, 0)
                    )
                ),
                Arguments.of(
                    27, arrayOf(
                        Item("+5 Dexterity Vest", -17, 0),
                        Item("Aged Brie", -25, 50),
                        Item("Elixir of the Mongoose", -22, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -12, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -17, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -22, 0),
                        Item("Conjured Mana Cake", -24, 0)
                    )
                ),
                Arguments.of(
                    28, arrayOf(
                        Item("+5 Dexterity Vest", -18, 0),
                        Item("Aged Brie", -26, 50),
                        Item("Elixir of the Mongoose", -23, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -13, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -18, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -23, 0),
                        Item("Conjured Mana Cake", -25, 0)
                    )
                ),
                Arguments.of(
                    29, arrayOf(
                        Item("+5 Dexterity Vest", -19, 0),
                        Item("Aged Brie", -27, 50),
                        Item("Elixir of the Mongoose", -24, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -14, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -19, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -24, 0),
                        Item("Conjured Mana Cake", -26, 0)
                    )
                ),
                Arguments.of(
                    30, arrayOf(
                        Item("+5 Dexterity Vest", -20, 0),
                        Item("Aged Brie", -28, 50),
                        Item("Elixir of the Mongoose", -25, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -15, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -20, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -25, 0),
                        Item("Conjured Mana Cake", -27, 0)
                    )
                ),
                Arguments.of(
                    31, arrayOf(
                        Item("+5 Dexterity Vest", -21, 0),
                        Item("Aged Brie", -29, 50),
                        Item("Elixir of the Mongoose", -26, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -16, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -21, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -26, 0),
                        Item("Conjured Mana Cake", -28, 0)
                    )
                ),
                Arguments.of(
                    32, arrayOf(
                        Item("+5 Dexterity Vest", -22, 0),
                        Item("Aged Brie", -30, 50),
                        Item("Elixir of the Mongoose", -27, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -17, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -22, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -27, 0),
                        Item("Conjured Mana Cake", -29, 0)
                    )
                ),
                Arguments.of(
                    33, arrayOf(
                        Item("+5 Dexterity Vest", -23, 0),
                        Item("Aged Brie", -31, 50),
                        Item("Elixir of the Mongoose", -28, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -18, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -23, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -28, 0),
                        Item("Conjured Mana Cake", -30, 0)
                    )
                ),
                Arguments.of(
                    34, arrayOf(
                        Item("+5 Dexterity Vest", -24, 0),
                        Item("Aged Brie", -32, 50),
                        Item("Elixir of the Mongoose", -29, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -19, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -24, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -29, 0),
                        Item("Conjured Mana Cake", -31, 0)
                    )
                ),
                Arguments.of(
                    35, arrayOf(
                        Item("+5 Dexterity Vest", -25, 0),
                        Item("Aged Brie", -33, 50),
                        Item("Elixir of the Mongoose", -30, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -20, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -25, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -30, 0),
                        Item("Conjured Mana Cake", -32, 0)
                    )
                ),
                Arguments.of(
                    36, arrayOf(
                        Item("+5 Dexterity Vest", -26, 0),
                        Item("Aged Brie", -34, 50),
                        Item("Elixir of the Mongoose", -31, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -21, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -26, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -31, 0),
                        Item("Conjured Mana Cake", -33, 0)
                    )
                ),
                Arguments.of(
                    37, arrayOf(
                        Item("+5 Dexterity Vest", -27, 0),
                        Item("Aged Brie", -35, 50),
                        Item("Elixir of the Mongoose", -32, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -22, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -27, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -32, 0),
                        Item("Conjured Mana Cake", -34, 0)
                    )
                ),
                Arguments.of(
                    38, arrayOf(
                        Item("+5 Dexterity Vest", -28, 0),
                        Item("Aged Brie", -36, 50),
                        Item("Elixir of the Mongoose", -33, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -23, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -28, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -33, 0),
                        Item("Conjured Mana Cake", -35, 0)
                    )
                ),
                Arguments.of(
                    39, arrayOf(
                        Item("+5 Dexterity Vest", -29, 0),
                        Item("Aged Brie", -37, 50),
                        Item("Elixir of the Mongoose", -34, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -24, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -29, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -34, 0),
                        Item("Conjured Mana Cake", -36, 0)
                    )
                ),
                Arguments.of(
                    40, arrayOf(
                        Item("+5 Dexterity Vest", -30, 0),
                        Item("Aged Brie", -38, 50),
                        Item("Elixir of the Mongoose", -35, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -25, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -30, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -35, 0),
                        Item("Conjured Mana Cake", -37, 0)
                    )
                ),
                Arguments.of(
                    41, arrayOf(
                        Item("+5 Dexterity Vest", -31, 0),
                        Item("Aged Brie", -39, 50),
                        Item("Elixir of the Mongoose", -36, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -26, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -31, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -36, 0),
                        Item("Conjured Mana Cake", -38, 0)
                    )
                ),
                Arguments.of(
                    42, arrayOf(
                        Item("+5 Dexterity Vest", -32, 0),
                        Item("Aged Brie", -40, 50),
                        Item("Elixir of the Mongoose", -37, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -27, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -32, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -37, 0),
                        Item("Conjured Mana Cake", -39, 0)
                    )
                ),
                Arguments.of(
                    43, arrayOf(
                        Item("+5 Dexterity Vest", -33, 0),
                        Item("Aged Brie", -41, 50),
                        Item("Elixir of the Mongoose", -38, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -28, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -33, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -38, 0),
                        Item("Conjured Mana Cake", -40, 0)
                    )
                ),
                Arguments.of(
                    44, arrayOf(
                        Item("+5 Dexterity Vest", -34, 0),
                        Item("Aged Brie", -42, 50),
                        Item("Elixir of the Mongoose", -39, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -29, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -34, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -39, 0),
                        Item("Conjured Mana Cake", -41, 0)
                    )
                ),
                Arguments.of(
                    45, arrayOf(
                        Item("+5 Dexterity Vest", -35, 0),
                        Item("Aged Brie", -43, 50),
                        Item("Elixir of the Mongoose", -40, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -30, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -35, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -40, 0),
                        Item("Conjured Mana Cake", -42, 0)
                    )
                ),
                Arguments.of(
                    46, arrayOf(
                        Item("+5 Dexterity Vest", -36, 0),
                        Item("Aged Brie", -44, 50),
                        Item("Elixir of the Mongoose", -41, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -31, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -36, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -41, 0),
                        Item("Conjured Mana Cake", -43, 0)
                    )
                ),
                Arguments.of(
                    47, arrayOf(
                        Item("+5 Dexterity Vest", -37, 0),
                        Item("Aged Brie", -45, 50),
                        Item("Elixir of the Mongoose", -42, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -32, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -37, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -42, 0),
                        Item("Conjured Mana Cake", -44, 0)
                    )
                ),
                Arguments.of(
                    48, arrayOf(
                        Item("+5 Dexterity Vest", -38, 0),
                        Item("Aged Brie", -46, 50),
                        Item("Elixir of the Mongoose", -43, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -33, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -38, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -43, 0),
                        Item("Conjured Mana Cake", -45, 0)
                    )
                ),
                Arguments.of(
                    49, arrayOf(
                        Item("+5 Dexterity Vest", -39, 0),
                        Item("Aged Brie", -47, 50),
                        Item("Elixir of the Mongoose", -44, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -34, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -39, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -44, 0),
                        Item("Conjured Mana Cake", -46, 0)
                    )
                ),
                Arguments.of(
                    50, arrayOf(
                        Item("+5 Dexterity Vest", -40, 0),
                        Item("Aged Brie", -48, 50),
                        Item("Elixir of the Mongoose", -45, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -35, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -40, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -45, 0),
                        Item("Conjured Mana Cake", -47, 0)
                    )
                ),
                Arguments.of(
                    51, arrayOf(
                        Item("+5 Dexterity Vest", -41, 0),
                        Item("Aged Brie", -49, 50),
                        Item("Elixir of the Mongoose", -46, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -36, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -41, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -46, 0),
                        Item("Conjured Mana Cake", -48, 0)
                    )
                ),
                Arguments.of(
                    52, arrayOf(
                        Item("+5 Dexterity Vest", -42, 0),
                        Item("Aged Brie", -50, 50),
                        Item("Elixir of the Mongoose", -47, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -37, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -42, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -47, 0),
                        Item("Conjured Mana Cake", -49, 0)
                    )
                ),
                Arguments.of(
                    53, arrayOf(
                        Item("+5 Dexterity Vest", -43, 0),
                        Item("Aged Brie", -51, 50),
                        Item("Elixir of the Mongoose", -48, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -38, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -43, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -48, 0),
                        Item("Conjured Mana Cake", -50, 0)
                    )
                ),
                Arguments.of(
                    54, arrayOf(
                        Item("+5 Dexterity Vest", -44, 0),
                        Item("Aged Brie", -52, 50),
                        Item("Elixir of the Mongoose", -49, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -39, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -44, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -49, 0),
                        Item("Conjured Mana Cake", -51, 0)
                    )
                ),
                Arguments.of(
                    55, arrayOf(
                        Item("+5 Dexterity Vest", -45, 0),
                        Item("Aged Brie", -53, 50),
                        Item("Elixir of the Mongoose", -50, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -40, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -45, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -50, 0),
                        Item("Conjured Mana Cake", -52, 0)
                    )
                ),
                Arguments.of(
                    56, arrayOf(
                        Item("+5 Dexterity Vest", -46, 0),
                        Item("Aged Brie", -54, 50),
                        Item("Elixir of the Mongoose", -51, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -41, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -46, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -51, 0),
                        Item("Conjured Mana Cake", -53, 0)
                    )
                ),
                Arguments.of(
                    57, arrayOf(
                        Item("+5 Dexterity Vest", -47, 0),
                        Item("Aged Brie", -55, 50),
                        Item("Elixir of the Mongoose", -52, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -42, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -47, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -52, 0),
                        Item("Conjured Mana Cake", -54, 0)
                    )
                ),
                Arguments.of(
                    58, arrayOf(
                        Item("+5 Dexterity Vest", -48, 0),
                        Item("Aged Brie", -56, 50),
                        Item("Elixir of the Mongoose", -53, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -43, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -48, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -53, 0),
                        Item("Conjured Mana Cake", -55, 0)
                    )
                ),
                Arguments.of(
                    59, arrayOf(
                        Item("+5 Dexterity Vest", -49, 0),
                        Item("Aged Brie", -57, 50),
                        Item("Elixir of the Mongoose", -54, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -44, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -49, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -54, 0),
                        Item("Conjured Mana Cake", -56, 0)
                    )
                ),
                Arguments.of(
                    60, arrayOf(
                        Item("+5 Dexterity Vest", -50, 0),
                        Item("Aged Brie", -58, 50),
                        Item("Elixir of the Mongoose", -55, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -45, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -50, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -55, 0),
                        Item("Conjured Mana Cake", -57, 0)
                    )
                ),
                Arguments.of(
                    61, arrayOf(
                        Item("+5 Dexterity Vest", -51, 0),
                        Item("Aged Brie", -59, 50),
                        Item("Elixir of the Mongoose", -56, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -46, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -51, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -56, 0),
                        Item("Conjured Mana Cake", -58, 0)
                    )
                ),
                Arguments.of(
                    62, arrayOf(
                        Item("+5 Dexterity Vest", -52, 0),
                        Item("Aged Brie", -60, 50),
                        Item("Elixir of the Mongoose", -57, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -47, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -52, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -57, 0),
                        Item("Conjured Mana Cake", -59, 0)
                    )
                ),
                Arguments.of(
                    63, arrayOf(
                        Item("+5 Dexterity Vest", -53, 0),
                        Item("Aged Brie", -61, 50),
                        Item("Elixir of the Mongoose", -58, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -48, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -53, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -58, 0),
                        Item("Conjured Mana Cake", -60, 0)
                    )
                ),
                Arguments.of(
                    64, arrayOf(
                        Item("+5 Dexterity Vest", -54, 0),
                        Item("Aged Brie", -62, 50),
                        Item("Elixir of the Mongoose", -59, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -49, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -54, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -59, 0),
                        Item("Conjured Mana Cake", -61, 0)
                    )
                ),
                Arguments.of(
                    65, arrayOf(
                        Item("+5 Dexterity Vest", -55, 0),
                        Item("Aged Brie", -63, 50),
                        Item("Elixir of the Mongoose", -60, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -50, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -55, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -60, 0),
                        Item("Conjured Mana Cake", -62, 0)
                    )
                ),
                Arguments.of(
                    66, arrayOf(
                        Item("+5 Dexterity Vest", -56, 0),
                        Item("Aged Brie", -64, 50),
                        Item("Elixir of the Mongoose", -61, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -51, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -56, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -61, 0),
                        Item("Conjured Mana Cake", -63, 0)
                    )
                ),
                Arguments.of(
                    67, arrayOf(
                        Item("+5 Dexterity Vest", -57, 0),
                        Item("Aged Brie", -65, 50),
                        Item("Elixir of the Mongoose", -62, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -52, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -57, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -62, 0),
                        Item("Conjured Mana Cake", -64, 0)
                    )
                ),
                Arguments.of(
                    68, arrayOf(
                        Item("+5 Dexterity Vest", -58, 0),
                        Item("Aged Brie", -66, 50),
                        Item("Elixir of the Mongoose", -63, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -53, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -58, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -63, 0),
                        Item("Conjured Mana Cake", -65, 0)
                    )
                ),
                Arguments.of(
                    69, arrayOf(
                        Item("+5 Dexterity Vest", -59, 0),
                        Item("Aged Brie", -67, 50),
                        Item("Elixir of the Mongoose", -64, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -54, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -59, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -64, 0),
                        Item("Conjured Mana Cake", -66, 0)
                    )
                ),
                Arguments.of(
                    70, arrayOf(
                        Item("+5 Dexterity Vest", -60, 0),
                        Item("Aged Brie", -68, 50),
                        Item("Elixir of the Mongoose", -65, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -55, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -60, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -65, 0),
                        Item("Conjured Mana Cake", -67, 0)
                    )
                ),
                Arguments.of(
                    71, arrayOf(
                        Item("+5 Dexterity Vest", -61, 0),
                        Item("Aged Brie", -69, 50),
                        Item("Elixir of the Mongoose", -66, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -56, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -61, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -66, 0),
                        Item("Conjured Mana Cake", -68, 0)
                    )
                ),
                Arguments.of(
                    72, arrayOf(
                        Item("+5 Dexterity Vest", -62, 0),
                        Item("Aged Brie", -70, 50),
                        Item("Elixir of the Mongoose", -67, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -57, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -62, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -67, 0),
                        Item("Conjured Mana Cake", -69, 0)
                    )
                ),
                Arguments.of(
                    73, arrayOf(
                        Item("+5 Dexterity Vest", -63, 0),
                        Item("Aged Brie", -71, 50),
                        Item("Elixir of the Mongoose", -68, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -58, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -63, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -68, 0),
                        Item("Conjured Mana Cake", -70, 0)
                    )
                ),
                Arguments.of(
                    74, arrayOf(
                        Item("+5 Dexterity Vest", -64, 0),
                        Item("Aged Brie", -72, 50),
                        Item("Elixir of the Mongoose", -69, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -59, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -64, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -69, 0),
                        Item("Conjured Mana Cake", -71, 0)
                    )
                ),
                Arguments.of(
                    75, arrayOf(
                        Item("+5 Dexterity Vest", -65, 0),
                        Item("Aged Brie", -73, 50),
                        Item("Elixir of the Mongoose", -70, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -60, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -65, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -70, 0),
                        Item("Conjured Mana Cake", -72, 0)
                    )
                ),
                Arguments.of(
                    76, arrayOf(
                        Item("+5 Dexterity Vest", -66, 0),
                        Item("Aged Brie", -74, 50),
                        Item("Elixir of the Mongoose", -71, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -61, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -66, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -71, 0),
                        Item("Conjured Mana Cake", -73, 0)
                    )
                ),
                Arguments.of(
                    77, arrayOf(
                        Item("+5 Dexterity Vest", -67, 0),
                        Item("Aged Brie", -75, 50),
                        Item("Elixir of the Mongoose", -72, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -62, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -67, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -72, 0),
                        Item("Conjured Mana Cake", -74, 0)
                    )
                ),
                Arguments.of(
                    78, arrayOf(
                        Item("+5 Dexterity Vest", -68, 0),
                        Item("Aged Brie", -76, 50),
                        Item("Elixir of the Mongoose", -73, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -63, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -68, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -73, 0),
                        Item("Conjured Mana Cake", -75, 0)
                    )
                ),
                Arguments.of(
                    79, arrayOf(
                        Item("+5 Dexterity Vest", -69, 0),
                        Item("Aged Brie", -77, 50),
                        Item("Elixir of the Mongoose", -74, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -64, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -69, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -74, 0),
                        Item("Conjured Mana Cake", -76, 0)
                    )
                ),
                Arguments.of(
                    80, arrayOf(
                        Item("+5 Dexterity Vest", -70, 0),
                        Item("Aged Brie", -78, 50),
                        Item("Elixir of the Mongoose", -75, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -65, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -70, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -75, 0),
                        Item("Conjured Mana Cake", -77, 0)
                    )
                ),
                Arguments.of(
                    81, arrayOf(
                        Item("+5 Dexterity Vest", -71, 0),
                        Item("Aged Brie", -79, 50),
                        Item("Elixir of the Mongoose", -76, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -66, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -71, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -76, 0),
                        Item("Conjured Mana Cake", -78, 0)
                    )
                ),
                Arguments.of(
                    82, arrayOf(
                        Item("+5 Dexterity Vest", -72, 0),
                        Item("Aged Brie", -80, 50),
                        Item("Elixir of the Mongoose", -77, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -67, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -72, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -77, 0),
                        Item("Conjured Mana Cake", -79, 0)
                    )
                ),
                Arguments.of(
                    83, arrayOf(
                        Item("+5 Dexterity Vest", -73, 0),
                        Item("Aged Brie", -81, 50),
                        Item("Elixir of the Mongoose", -78, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -68, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -73, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -78, 0),
                        Item("Conjured Mana Cake", -80, 0)
                    )
                ),
                Arguments.of(
                    84, arrayOf(
                        Item("+5 Dexterity Vest", -74, 0),
                        Item("Aged Brie", -82, 50),
                        Item("Elixir of the Mongoose", -79, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -69, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -74, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -79, 0),
                        Item("Conjured Mana Cake", -81, 0)
                    )
                ),
                Arguments.of(
                    85, arrayOf(
                        Item("+5 Dexterity Vest", -75, 0),
                        Item("Aged Brie", -83, 50),
                        Item("Elixir of the Mongoose", -80, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -70, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -75, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -80, 0),
                        Item("Conjured Mana Cake", -82, 0)
                    )
                ),
                Arguments.of(
                    86, arrayOf(
                        Item("+5 Dexterity Vest", -76, 0),
                        Item("Aged Brie", -84, 50),
                        Item("Elixir of the Mongoose", -81, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -71, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -76, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -81, 0),
                        Item("Conjured Mana Cake", -83, 0)
                    )
                ),
                Arguments.of(
                    87, arrayOf(
                        Item("+5 Dexterity Vest", -77, 0),
                        Item("Aged Brie", -85, 50),
                        Item("Elixir of the Mongoose", -82, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -72, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -77, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -82, 0),
                        Item("Conjured Mana Cake", -84, 0)
                    )
                ),
                Arguments.of(
                    88, arrayOf(
                        Item("+5 Dexterity Vest", -78, 0),
                        Item("Aged Brie", -86, 50),
                        Item("Elixir of the Mongoose", -83, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -73, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -78, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -83, 0),
                        Item("Conjured Mana Cake", -85, 0)
                    )
                ),
                Arguments.of(
                    89, arrayOf(
                        Item("+5 Dexterity Vest", -79, 0),
                        Item("Aged Brie", -87, 50),
                        Item("Elixir of the Mongoose", -84, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -74, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -79, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -84, 0),
                        Item("Conjured Mana Cake", -86, 0)
                    )
                ),
                Arguments.of(
                    90, arrayOf(
                        Item("+5 Dexterity Vest", -80, 0),
                        Item("Aged Brie", -88, 50),
                        Item("Elixir of the Mongoose", -85, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -75, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -80, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -85, 0),
                        Item("Conjured Mana Cake", -87, 0)
                    )
                ),
                Arguments.of(
                    91, arrayOf(
                        Item("+5 Dexterity Vest", -81, 0),
                        Item("Aged Brie", -89, 50),
                        Item("Elixir of the Mongoose", -86, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -76, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -81, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -86, 0),
                        Item("Conjured Mana Cake", -88, 0)
                    )
                ),
                Arguments.of(
                    92, arrayOf(
                        Item("+5 Dexterity Vest", -82, 0),
                        Item("Aged Brie", -90, 50),
                        Item("Elixir of the Mongoose", -87, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -77, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -82, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -87, 0),
                        Item("Conjured Mana Cake", -89, 0)
                    )
                ),
                Arguments.of(
                    93, arrayOf(
                        Item("+5 Dexterity Vest", -83, 0),
                        Item("Aged Brie", -91, 50),
                        Item("Elixir of the Mongoose", -88, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -78, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -83, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -88, 0),
                        Item("Conjured Mana Cake", -90, 0)
                    )
                ),
                Arguments.of(
                    94, arrayOf(
                        Item("+5 Dexterity Vest", -84, 0),
                        Item("Aged Brie", -92, 50),
                        Item("Elixir of the Mongoose", -89, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -79, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -84, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -89, 0),
                        Item("Conjured Mana Cake", -91, 0)
                    )
                ),
                Arguments.of(
                    95, arrayOf(
                        Item("+5 Dexterity Vest", -85, 0),
                        Item("Aged Brie", -93, 50),
                        Item("Elixir of the Mongoose", -90, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -80, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -85, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -90, 0),
                        Item("Conjured Mana Cake", -92, 0)
                    )
                ),
                Arguments.of(
                    96, arrayOf(
                        Item("+5 Dexterity Vest", -86, 0),
                        Item("Aged Brie", -94, 50),
                        Item("Elixir of the Mongoose", -91, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -81, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -86, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -91, 0),
                        Item("Conjured Mana Cake", -93, 0)
                    )
                ),
                Arguments.of(
                    97, arrayOf(
                        Item("+5 Dexterity Vest", -87, 0),
                        Item("Aged Brie", -95, 50),
                        Item("Elixir of the Mongoose", -92, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -82, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -87, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -92, 0),
                        Item("Conjured Mana Cake", -94, 0)
                    )
                ),
                Arguments.of(
                    98, arrayOf(
                        Item("+5 Dexterity Vest", -88, 0),
                        Item("Aged Brie", -96, 50),
                        Item("Elixir of the Mongoose", -93, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -83, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -88, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -93, 0),
                        Item("Conjured Mana Cake", -95, 0)
                    )
                ),
                Arguments.of(
                    99, arrayOf(
                        Item("+5 Dexterity Vest", -89, 0),
                        Item("Aged Brie", -97, 50),
                        Item("Elixir of the Mongoose", -94, 0),
                        Item("Sulfuras, Hand of Ragnaros", 0, 80),
                        Item("Sulfuras, Hand of Ragnaros", -1, 80),
                        Item("Backstage passes to a TAFKAL80ETC concert", -84, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -89, 0),
                        Item("Backstage passes to a TAFKAL80ETC concert", -94, 0),
                        Item("Conjured Mana Cake", -96, 0)
                    )
                ),
            )

        private fun createItems() =
            arrayOf(
                Item("+5 Dexterity Vest", 10, 20), //
                Item("Aged Brie", 2, 0), //
                Item("Elixir of the Mongoose", 5, 7), //
                Item("Sulfuras, Hand of Ragnaros", 0, 80), //
                Item("Sulfuras, Hand of Ragnaros", -1, 80),
                Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
                Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
                // this conjured item does not work properly yet
                Item("Conjured Mana Cake", 3, 6)
            )

    }
}


