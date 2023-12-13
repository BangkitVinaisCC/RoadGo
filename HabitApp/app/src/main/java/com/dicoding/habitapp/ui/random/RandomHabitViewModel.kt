package com.dicoding.habitapp.ui.random

import androidx.lifecycle.*
import androidx.paging.PagedList
import com.dicoding.habitapp.data.Habit
import com.dicoding.habitapp.data.HabitRepository

class RandomHabitViewModel (habitRepository: HabitRepository) : ViewModel() {
    val priorityLevelHigh: LiveData<PagedList<Habit>> = habitRepository.getRandomHabitByPriorityLevel("High")
    val priorityLevelMedium: LiveData<PagedList<Habit>> = habitRepository.getRandomHabitByPriorityLevel("Medium")
    val priorityLevelLow: LiveData<PagedList<Habit>> = habitRepository.getRandomHabitByPriorityLevel("Low")
}