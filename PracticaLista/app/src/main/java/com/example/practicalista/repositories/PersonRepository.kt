package com.example.practicalista.repositories

import com.example.practicalista.models.Person

object PersonRepository {
    private val people = arrayListOf(
        Person(1, "Juan", "Pérez", "1234567890", "jperez@test.com", "Calle Falsa 123"),
        Person(2, "Juan", "Pérezito", "1234567890", "jperez2@test.com", "Calle Falsa 123"),
        Person(3, "Pepe", "Pecas", "32165987", "ppecas@test.com", "Calle Falsa 124"),
        Person(4, "Juan", "Pérez", "1234567890", "jperez@test.com", "Calle Falsa 123"),
        Person(5, "Juan", "Pérezito", "1234567890", "jperez2@test.com", "Calle Falsa 123"),
        Person(6, "Pepe", "Pecas", "32165987", "ppecas@test.com", "Calle Falsa 124"),
        Person(7, "Juan", "Pérez", "1234567890", "jperez@test.com", "Calle Falsa 123"),
        Person(8, "Juan", "Pérezito", "1234567890", "jperez2@test.com", "Calle Falsa 123"),
        Person(9, "Pepe", "Pecas", "32165987", "ppecas@test.com", "Calle Falsa 124"),
        Person(10, "Juan", "Pérez", "1234567890", "jperez@test.com", "Calle Falsa 123"),
        Person(11, "Juan", "Pérezito", "1234567890", "jperez2@test.com", "Calle Falsa 123"),
        Person(12, "Pepe", "Pecas", "32165987", "ppecas@test.com", "Calle Falsa 124"),
        Person(13, "Juan", "Pérez", "1234567890", "jperez@test.com", "Calle Falsa 123"),
        Person(14, "Juan", "Pérezito", "1234567890", "jperez2@test.com", "Calle Falsa 123"),
        Person(15, "Pepe", "Pecas", "32165987", "ppecas@test.com", "Calle Falsa 124"),
    )

    fun getPeople(): ArrayList<Person> {
        return people.clone() as ArrayList<Person>
    }

    fun savePerson(person: Person) {
        val index = people.indexOfFirst { it.id == person.id }
        if (index == -1) {
            people.add(1, person)
        } else {
            people[index] = person
        }
    }

    fun deleteItem(person: Person) {
        val index = people.indexOfFirst { it.id == person.id }
        if (index != -1) {
            people.removeAt(index)
        }
    }

    fun getPersonById(id: Int): Person? {
        return people.find { it.id == id }
    }
}