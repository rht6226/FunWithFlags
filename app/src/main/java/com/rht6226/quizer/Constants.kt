package com.rht6226.quizer

object Constants {

    fun getQuestions(): ArrayList<Question> {
        val questionsList = ArrayList<Question>()
        val questionString = "What country does this flag belong to?"

        val que1 = Question(
            1, questionString,
            R.drawable.ic_flag_of_argentina, "Argentina", "Austria",
            "Armenia", "Australia", 1
        )

        val que2 = Question(
            2, questionString,
            R.drawable.ic_flag_of_india, "Argentina", "India",
            "United States of America", "Japan", 2
        )

        val que3 = Question(
            3, questionString,
            R.drawable.ic_flag_of_belgium, "Fiji", "New Zealand",
            "Denmark", "Belgium", 4
        )

        val que4 = Question(
            4, questionString,
            R.drawable.ic_flag_of_kuwait, "United Arab Emirates", "Denmark",
            "Kuwait", "Ghana", 3
        )

        val que5 = Question(
            5, questionString,
            R.drawable.ic_flag_of_fiji, "Egypt", "Fiji",
            "Armenia", "Bangladesh", 2
        )

        val que6 = Question(
            6, questionString,
            R.drawable.ic_flag_of_australia, "United Kingdom", "New Zealand",
            "United States of America", "Australia", 4
        )

        val que7 = Question(
            7, questionString,
            R.drawable.ic_flag_of_denmark, "Norway", "Italy",
            "Denmark", "Sweden", 3
        )

        val que8 = Question(
            8, questionString,
            R.drawable.ic_flag_of_brazil, "Brazil", "Columbia",
            "Denmark", "Spain", 1
        )

        val que9 = Question(
            9, questionString,
            R.drawable.ic_flag_of_germany, "Russia", "Germany",
            "Croatia", "South Africa", 2
        )

        val que10 = Question(
            8, questionString,
            R.drawable.ic_flag_of_new_zealand, "Australia", "United Kingdom",
            "Ireland", "new Zealand", 4
        )

        questionsList.add(que1)
        questionsList.add(que2)
        questionsList.add(que3)
        questionsList.add(que4)
        questionsList.add(que5)
        questionsList.add(que6)
        questionsList.add(que7)
        questionsList.add(que8)
        questionsList.add(que9)
        questionsList.add(que10)

        return questionsList
    }
}