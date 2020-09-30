# OpenMovieApp

OpenMovieApp is the simple Android application that allows you to search movies using OMDb API. 

# Build

Before building, please, obtain an api-key from http://www.omdbapi.com/ and put it to build configs.

buildTypes {
        release {
            // ...

            buildConfigField 'String', 'OMDB_API_KEY', '"<your_key>"'
        }

        debug {
            buildConfigField 'String', 'OMDB_API_KEY', '"<your_key>"'
        }
    }
