package com.acchao.portfolio.data

import androidx.compose.runtime.Immutable
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.UUID


@Immutable
data class Skill(
    val name: String,
    val years: Int = 0,
    val id: String = UUID.randomUUID().toString()
)

@Immutable
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
    val id: String = UUID.randomUUID().toString()
) {
    private val timeFormatter: DateTimeFormatter = DateTimeFormatter.ISO_DATE
    val startDate: LocalDate = LocalDate.parse(start, timeFormatter)
    val endDate: LocalDate = LocalDate.parse(end, timeFormatter)

    fun getYears() : Int  {
        val period = Period.between(startDate, endDate)
        return period.years
    }
}

@Immutable
data class Bullet(
    val description: String,
    val key: Boolean = false,
    val skills: List<Skill> = emptyList(),
    val id: String = UUID.randomUUID().toString()
)

data class Tag(val name: String)

@Immutable
data class Education(
    val schoolName: String,
    val degree: String,
    val start: String, //ISO 8601 yyyy-mm-dd
    val end: String,
    val graduation: Int,
    val id: String = UUID.randomUUID().toString()
)


@Immutable
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
    Experience(
        title = "Android Engineer",
        company = "Roomi",
        location = "NYC",
        start = "2016-10-15",
        end = "2018-04-15",
        skills = listOf(Skill("Android"), Skill("Java"), Skill("RxJava"),
            Skill("RecyclerView"), Skill("Glide"), Skill("Data Binding"),
            Skill("Retrofit")),
        bullets = listOf(
            Bullet(
                description = "Built features such as identity verification, background checks, 2 factor authentication, " +
                        "maps clustering, onboarding, full screen image viewer, deep linking",
                key = true
            ),
            Bullet(
                description = "Redesigned the navigation flows of the Roomi app to increase user engagement",
                key = true
            ),
            Bullet(
                description = "Re-factored the app to be cleaner and maintainable: resulted in 99% crash free from 93%",
                key = true
            ),
            Bullet(
                description = "Mentored an Android Intern",
                key = false
            ),
            Bullet(
                description = "Actively improving our engineering processes (e.g. commit templates, new hire docs)",
                key = false
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