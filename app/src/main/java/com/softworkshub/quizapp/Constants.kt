package com.softworkshub.quizapp

object Constants {

    fun getQuestion():ArrayList<Question>{

        val questionList = ArrayList<Question>()

        val qu1 = Question(
            1,"What county does this flag belong to ? ",
            R.drawable.flag_of_india,
            "India","America","Pakistan","Japan",
            1

        )
        questionList.add(qu1)

        val qu2  = Question(
            2, "Which is your favorite game ? ",
            R.drawable.flag_of_india,
            "Cricket","FootBall","BollyBol","Chess",
            2
        )
        questionList.add(qu2)

        val qu3  = Question(
            2, "Which is your favorite food ? ",
            R.drawable.flag_of_india,
            "Aalo","KELa","BAigan","GOBhi",
            4
        )
        questionList.add(qu3)

        val qu4  = Question(
            2, "Which is your favorite Car ? ",
            R.drawable.flag_of_india,
            "BMW","Lamborghini","MERCEDES","Swift",
            3
        )
        questionList.add(qu4)

        val qu5  = Question(
            2, "Which is your favorite Car ? ",
            R.drawable.flag_of_india,
            "BMW","Lamborghini","MERCEDES","Swift",
            3
        )
        questionList.add(qu5)

        val qu6  = Question(
            2, "Which is your favorite Car ? ",
            R.drawable.flag_of_india,
            "BMW","Lamborghini","MERCEDES","Swift",
            3
        )
        questionList.add(qu6)

        val qu7  = Question(
            2, "Which is your favorite Car ? ",
            R.drawable.flag_of_india,
            "BMW","Lamborghini","MERCEDES","Swift",
            3
        )
        questionList.add(qu7)

        val qu8  = Question(
            2, "Which is your favorite Car ? ",
            R.drawable.flag_of_india,
            "BMW","Lamborghini","MERCEDES","Swift",
            3
        )
        questionList.add(qu8)

        val qu9  = Question(
            2, "Which is your favorite Car ? ",
            R.drawable.flag_of_india,
            "BMW","Lamborghini","MERCEDES","Swift",
            3
        )
        questionList.add(qu9)

        val qu10  = Question(
            2, "Which is your favorite Car ? ",
            R.drawable.flag_of_india,
            "BMW","Lamborghini","MERCEDES","Swift",
            3
        )
        questionList.add(qu10)

        return  questionList
    }

}