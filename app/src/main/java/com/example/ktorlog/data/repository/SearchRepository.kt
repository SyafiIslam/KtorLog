package com.example.ktorlog.data.repository

import android.os.Trace
import android.util.Log
import com.example.ktorlog.data.remote.response.list.FilmListResponse
import com.example.ktorlog.domain.repository.ISearchRepository
import com.example.ktorlog.util.Resource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchRepository(
    private val api: HttpClient
): ISearchRepository {

    private val searchEndpoint= "/search"

    override suspend fun browseMovies(
        query: String,
        type: String
    ): Flow<Resource<FilmListResponse>> =
        flow {
            emit(Resource.Loading())
            Trace.beginSection("API: browseMovies")

            val result =
                try {
                    val resp = api.get("$searchEndpoint/$type") {
                        url {
                            parameters.append("query", query)
                        }
                    }.body<FilmListResponse>()

                    Resource.Success(resp)
                } catch (e: RedirectResponseException) {
                    Resource.Error(e.response.status.description)
                } catch (e: ClientRequestException) {
                    Resource.Error(e.response.status.description)
                } catch (e: ServerResponseException) {
                    Resource.Error(e.response.status.description)
                } catch (e: Exception) {
                    Resource.Error(e.message.toString())
                } finally {
                    Trace.endSection()
                }

            emit(result)
        }
}
