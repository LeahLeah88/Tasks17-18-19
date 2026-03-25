package com.example.task17.data

import com.example.task17.data.network.CharacterDto
import com.example.task17.data.network.NetworkModule

class CharacterRepository {
    suspend fun getCharactersPage(page: Int): List<CharacterDto> {
        return NetworkModule.api.getCharacters(page = page).results
    }

    suspend fun searchCharacters(name: String): List<CharacterDto> {
        return NetworkModule.api.getCharacters(name = name).results
    }

    suspend fun getCharacterById(id: Int): CharacterDto {
        return NetworkModule.api.getCharacterById(id)
    }
}
