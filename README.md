# Gilded Rose Refactoring Kata

This is my execution of the [Gilded Rose Refactoring Kata](https://github.com/emilybache/GildedRose-Refactoring-Kata), done in **Kotlin**.

My main goal doing this kata was to make the items' update behavior configurable, declarable -- because that's what I got from the requirements. New types of items will surely have to be added later on, bringing their own specific requirements. I believe that with a configuration approach, this will be much easier to maintain than in the original code, where requirements were scattered over an already complex calculation function. Even when new configuration options will be needed, it will be easier to extend the new ItemConfig class than to try and work them into the original code.

Easier to test as well. The fork now contains a working unit test, based on the original program's execution over 100 update cycles. Even so, I think the separate classes should get their own unit tests (in a next version).

It may be arguable that the item configuration should be external to the code, for example in a properties file or a script file. Thus allowing configuration change without the need to re-deploy. More specific (non-functional) requirements would be decisive in this choice. For now, I chose to do the config in code, because I wanted to show how Kotlin can be used to create this kind of configurability.
