package com.acchao.portfolio.data

import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter


data class Skill(
    val name: String,
    val years: Int = 0
)

data class Experience(
    val title: String,
    val company: String,
    val location: String,
    val start: String, //ISO 8601 yyyy-mm-dd
    val end: String,
    val skills: List<Skill> = emptyList(),
    val bullets: List<Bullet> = emptyList(),
    val link: String? = null,
    val logo: String? = null,
) {
    private val timeFormatter: DateTimeFormatter = DateTimeFormatter.ISO_DATE
    val startDate: LocalDate = LocalDate.parse(start, timeFormatter)
    val endDate: LocalDate = LocalDate.parse(end, timeFormatter)

    fun getYears() : Int  {
        val period = Period.between(startDate, endDate)
        return period.years
    }
}

data class Bullet(
    val description: String,
    val key: Boolean = false,
    val skills: List<Skill> = emptyList(),
)

data class Tag(val name: String)

data class Education(
    val schoolName: String,
    val degree: String,
    val start: String, //ISO 8601 yyyy-mm-dd
    val end: String,
    val graduation: Int
)

data class Portfolio(
    val education: List<Education>,
    val experience: List<Experience>,
    val skills: List<Skill>
)

val listOfEducation = listOf(
    Education(
        schoolName = "UCLA",
        degree = "B.S. Electrical Engineering",
        start = "2004",
        end = "2008",
        graduation = 2008
    ),
    Education(
        schoolName = "UC Berkeley",
        degree = "Masters of Information Management and Systems",
        start = "2011",
        end = "2013",
        graduation = 2013
    ),
)

val listOfExperience = listOf(
    Experience(
        title = "Senior Software Engineer",
        company = "Foursquare",
        location = "NYC",
        start = "2018-04-15",
        end = "2021-04-15",
        skills = listOf(Skill("Android"), Skill("Kotlin"), Skill("RxJava")),
        bullets = listOf(
            Bullet(
                description = "Only Android engineer in a team of 3, I was in charge of 5 Android applications including Foursquare and Swarm.",
                key = true
            ),
            Bullet(
                description = "Worked with founder Dennis Crowley to showcase Foursquare data with a private app called Hypertrending at SXSW",
                key = true
            ),
            Bullet(
                description = "Launched the ability to search historical check-ins based on date for Android",
                key = true
            ),
        )
    ),
)

val listOfSkills = listOf(
    Skill("Java", 5),
    Skill("Kotlin", 7),
)

val portfolio =  Portfolio(
    education = listOfEducation,
    experience = listOfExperience,
    skills = listOfSkills
)