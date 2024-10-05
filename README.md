# Overview


This is a simple console window application for creating and organizing recipes (food recipes).

The recipes are stored in plain json, and the user can search or view them.

I made this little tool in order to introduce myself to kotlin; I am familiar with Java, and had heard it's child was a lot friendlier. I agree!

I'm quite impressed with the language, there wasn't any friction to writing the code really.

[Software Demo Video](https://youtu.be/MO2zsuNbP5g)

# Development Environment

I used the IntelliJ community code editor. The tab completion works great.

This is made in Kotlin, built with gradle. Other than the standard libraries I used the Kotlinx serialization plugin, which allowed me to turn the object into json nice and easy.

# Useful Websites

These are kind of unfinished, but are what I used to figure out the language.

- [Kotlin Quick Reference](https://kotlin-quick-reference.com/)
- [Kotlin Documentation](https://kotlinlang.org/)

# Future Work

- The code is a bit messy; It could stand to be refactored and trimmed down.
- It would be really fun if this was made into a windowed application instead of living in the console.
- Right now the 'edit recipe' option just opens up the json using the default application. Preferably I would bundle in some basic json editor.
- I would also like to add the abillity to leave a 'review', ie. if I prepare the food I would want to leave additional notes to say what went (or didn't go) well.