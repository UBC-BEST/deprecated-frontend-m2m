package com.example.tutorial.shared


class M2MUser(var trainingSelection: ArrayList<String>) {

    // dds training to user. if the training is already in the list, it will remove and return
    // false. if it is not been added to the list it will add and return true
    fun addTraining(training: String): Boolean {
        val hasTraining = trainingSelection.contains(training)
        if (!hasTraining) {
            trainingSelection.add(training)
            return true
        } else if (hasTraining) {
            trainingSelection.remove(training)
            return false
        }
        return false
    }
}
