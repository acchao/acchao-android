package com.acchao.portfolio.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.acchao.portfolio.R
import com.acchao.portfolio.data.Education
import com.acchao.portfolio.data.Experience
import com.acchao.portfolio.data.Skill
import com.acchao.portfolio.ui.theme.PortfolioTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(stringResource(R.string.andrew_chao))
                }
            )
        }
    ) {
        HomeContent(it)
    }
}

@Composable
fun HomeContent(innerPadding: PaddingValues, modifier: Modifier = Modifier) {
    Column (
        modifier = Modifier.padding(innerPadding)
            .padding(horizontal = 8.dp)
            .fillMaxSize()
    ){
        Intro()
        StatusBar()
        Title("Skills")
        SkillsChart(mutableListOf(Skill("android", 13)))
        Title("Experience")
        ExperienceSection(mutableListOf(Experience("Quora", 13)))
        Title("Education")
        EducationSection(mutableListOf(Education("UCLA", "BS Electrical Engineering")))
    }
}

@Composable
fun Intro(modifier: Modifier = Modifier) {
    Text(stringResource(R.string.intro))
}

@Composable
fun StatusBar(modifier: Modifier = Modifier) {
    Row {
        Text(stringResource(R.string.status))
        Text(stringResource(R.string.looking_for_work))
    }

}

@Composable
fun Title(string: String, modifier: Modifier = Modifier) {
    Text(string, fontWeight = FontWeight.Bold)
}

@Composable
fun SkillsChart(skills: List<Skill>, modifier: Modifier = Modifier) {
    LazyColumn {
        items(items = skills, itemContent = {
            SkillBar(it)
        })
    }
}

@Composable
fun SkillBar(skill: Skill) {
    Row {
        Text(skill.name)
        Text(pluralStringResource(id = R.plurals.skill_years, skill.years, skill.years))
    }
}

@Composable
fun ExperienceSection(experiences: List<Experience>, modifier: Modifier = Modifier) {
    LazyColumn {
        items(items = experiences, itemContent = {
            ExperienceRow(it)
        })
    }
}

@Composable
fun ExperienceRow(experience: Experience) {
    Row {
        Text(experience.name)
        Text(pluralStringResource(id = R.plurals.skill_years, experience.years, experience.years))
    }
}

@Composable
fun EducationSection(education: List<Education>, modifier: Modifier = Modifier) {
    LazyColumn {
        items(items = education, itemContent = {
            EducationRow(it)
        })
    }
}

@Composable
fun EducationRow(edu: Education) {
    Row {
        Text(edu.schoolName)
        Text(edu.degree)
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 600)
@Composable
fun HomeScreenPreview() {
    PortfolioTheme {
        HomeScreen()
    }
}