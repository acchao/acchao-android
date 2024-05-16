package com.acchao.portfolio.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Chip
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.acchao.portfolio.R
import com.acchao.portfolio.data.Bullet
import com.acchao.portfolio.data.Education
import com.acchao.portfolio.data.Experience
import com.acchao.portfolio.data.Skill
import com.acchao.portfolio.data.listOfExperience
import com.acchao.portfolio.data.portfolio
import com.acchao.portfolio.ui.theme.LightGrey
import com.acchao.portfolio.ui.theme.PortfolioTheme
import com.acchao.portfolio.ui.theme.Teal
import com.acchao.portfolio.viewmodel.PortfolioViewModel
import com.acchao.portfolio.viewmodel.PortfolioViewModel.ResumeUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: PortfolioViewModel = viewModel(), modifier: Modifier = Modifier) {

    val portfolioState by viewModel.portfolio.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(stringResource(R.string.andrew_chao))
                }
            )
        }
    ) { paddingValues ->
        HomeContent(
            portfolioState,
            paddingValues,
            modifier
        )
    }
}

@Composable
fun HomeContent(
    resumeState: PortfolioViewModel.ResumeUiState,
    innerPadding: PaddingValues,
    modifier: Modifier = Modifier) {
    // TODO: explore implementation of AndroidView() so we can leverage a recyclerview which currently
    // has better scroll performance.
    // https://medium.com/androiddevelopers/jetpack-compose-interop-using-compose-in-a-recyclerview-569c7ec7a583
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
            .padding(innerPadding)
            .padding(horizontal = 12.dp)
            .fillMaxSize()
    ) {

        intro()
        statusBar()
        filterableResume(resumeState)
    }
}


fun LazyListScope.filterableResume(
    resumeState: ResumeUiState,
    modifier: Modifier = Modifier
) {
    when (resumeState) {
        ResumeUiState.Loading -> {
            item {
                Box(modifier) {
                    Text("Oops, wait a minute... I'm pretty sure I have more experience than this")
                }
            }
        }
        is ResumeUiState.Success -> {
            item {

                Title("Skills")
                SkillsRow(resumeState.portfolio.skills.toSet(), showYears = true)
            }
            stickyTitle("Experience")
            experienceSection(resumeState.portfolio.experience)
            stickyTitle("Education")
            educationSection(resumeState.portfolio.education)
        }
    }
}

fun LazyListScope.intro(modifier: Modifier = Modifier) {
    item {
        Text(stringResource(R.string.intro))
    }
}

fun LazyListScope.statusBar(modifier: Modifier = Modifier) {
    item {
        Row(modifier) {
            Text(stringResource(R.string.status))
            Text(stringResource(R.string.looking_for_work))
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
fun LazyListScope.stickyTitle(titleText: String, modifier: Modifier = Modifier) {
    stickyHeader {
        Title(titleText, modifier)
    }
}

@Composable
fun Title(titleText: String, modifier: Modifier = Modifier) {
    Surface(modifier = Modifier.fillMaxWidth()) {
        Text(
            titleText,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.h6,
            modifier = modifier
        )
    }
}

@Composable
fun SkillsChart(
    skills: List<Skill>,
    viewModel: PortfolioViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier) {
        items(
            items = skills,
            key = { skill -> skill.id },
            itemContent = {
            SkillChip(skill = it) {
                viewModel.addFilter(it)
            }
        })
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SkillChip(skill: Skill, onClick: () -> Unit) {
    Chip(onClick = onClick) {
        Text(skill.name, modifier = Modifier.padding(end = 8.dp))
        Text(pluralStringResource(id = R.plurals.skill_years, skill.years, skill.years))
    }
}

fun LazyListScope.experienceSection(experiences: List<Experience>) {
    items(
        items = experiences,
        key = { it.id },
        itemContent = {
            ExperienceRow(it)
        }
    )
}

@Composable
fun ExperienceRow(experience: Experience) {
    val selectedSkills = remember { mutableStateListOf<Skill>() }

    Card {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    experience.title,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1F)
                )
                Text(
                    "${experience.startDate.year} - ${experience.endDate.year}"
//            pluralStringResource(id = R.plurals.skill_years, experience.getYears(), experience.getYears())
                )
            }
            Text(experience.company)
            SkillsRow(experience.skills.toSet(), selectedSkills = selectedSkills) {
                if (selectedSkills.contains(it)) {
                    selectedSkills.remove(it)
                } else {
                    selectedSkills.add(it)
                }
            }
            for (bullet in experience.bullets) {
                Bullet(bullet)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewExperienceRow() {
    PortfolioTheme {
        ExperienceRow(experience = listOfExperience.first())
    }
}

@Composable
fun Bullet(bullet: Bullet) {
    Row {
        Text("\u2022", modifier = Modifier.padding(start = 12.dp, end = 8.dp))
        Text(bullet.description)
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SkillsRow(
    skills: Set<Skill>,
    modifier: Modifier = Modifier,
    showYears: Boolean = false,
    selectedSkills: List<Skill> = emptyList(),
    updateSelectedSkills: (Skill) -> Unit = {},
) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        for (skill in skills) {
            SkillPill(
                skill = skill,
                isSelected = selectedSkills.contains(skill),
                expand = showYears,
                onClick = updateSelectedSkills
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSkillsRow() {
    PortfolioTheme {
        SkillsRow(portfolio.skills.toSet(), showYears = false)
    }
}

@Composable
fun SkillPill(
    skill: Skill,
    isSelected: Boolean,
    expand: Boolean,
    onClick: (skill: Skill) -> Unit,
    modifier: Modifier = Modifier,
) {
    val pillText = if (expand && skill.years > 0) {
        "${ skill.name } | ${ skill.years } years"
    } else {
        "${ skill.name }"
    }

    if (expand) {
        ElevatedButton(
            onClick = { onClick(skill) },
            modifier
        ) {
            Text(
                text = pillText
            )
        }
    } else {
        val containerColor = if(isSelected) {
            Teal
        } else {
            LightGrey
        }

        val textColor = if(isSelected) {
            Color.White
        } else {
            MaterialTheme.colors.onBackground
        }

        FilledTonalButton(
            onClick = { onClick(skill) },
            colors = ButtonDefaults.buttonColors(
                containerColor = containerColor,
                contentColor = textColor
            ),
            contentPadding = PaddingValues(vertical = 4.dp, horizontal = 12.dp),
            modifier = modifier
                .defaultMinSize(minWidth = 1.dp, minHeight = 1.dp)
        ) {
            Text(
                text = pillText
            )
        }
    }
}


fun LazyListScope.educationSection(education: List<Education>, modifier: Modifier = Modifier) {
    item {
        Card(modifier) {
            var size by remember { mutableStateOf(IntSize.Zero) }
            Column {
                for (edu in education) {
                    EducationRow(edu = edu, alignmentWidth = size.width) { measuredSize ->
                        size = measuredSize
                    }
                }
            }
        }
    }
}

@Composable
fun EducationRow(
    edu: Education,
    alignmentWidth: Int,
    modifier: Modifier = Modifier,
    updateWidth: (IntSize) -> Unit
) {
    // Note(Andrew): layout is intentionally different here to mess around with creating a tab aligned
    // grid. I may want to look into subcompose layouts. Not sure how this implementation will scale
    // beyond 2 items right now. Deeper understanding of measure and draw is needed.
    Row(modifier = modifier.padding(12.dp)) {
        Column {
            Text(
                edu.schoolName,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .onGloballyPositioned {
                        if (alignmentWidth < it.size.width) {
                            updateWidth(it.size)
                        }
                    }
                    .conditional(alignmentWidth > 0, {
                        width(
                            with(LocalDensity.current) { alignmentWidth.toDp() }
                        )
                    })
            )
            Text(edu.end, style = TextStyle(color = Color.Gray))
        }
        Spacer(modifier = Modifier.width(12.dp))
        Text(edu.degree)
    }
}


inline fun Modifier.conditional(
    condition: Boolean,
    ifTrue: Modifier.() -> Modifier,
    ifFalse: Modifier.() -> Modifier = { this },
): Modifier = if (condition) {
    then(ifTrue(Modifier))
} else {
    then(ifFalse(Modifier))
}

@Preview(showBackground = true, widthDp = 480, heightDp = 1000)
@Composable
fun PreviewHomeContent() {
    PortfolioTheme {
        HomeContent(ResumeUiState.Success(portfolio), PaddingValues())
    }
}