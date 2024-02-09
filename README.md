# GitHub Repository fetching API

This API allows you to retrieve information about GitHub repositories based on the provided username. It follows the acceptance criteria outlined below.

## How to Use

### Getting Repository Information

To get information about GitHub repositories for a specific user, send a GET request to the following endpoint wiht `username` as param:

`GET /repositories?username={username}`
`Port: 8080`


Include the header `Accept: application/json` in your request to receive a JSON response.

### Response Format

The API will respond with a JSON object containing the following information for each repository:

- Repository name
- Owner login
- Branches:
    - Name
    - Last commit SHA

### Handling Non-existing Users

If the provided GitHub user does not exist, the API will return a 404 response in the following format:

```json
{
    "status": ${responseCode},
    "message": ${whyHasItHappened}
}
```
#

This project is built using Spring Boot framework 3 and Java 21.
The backing API used is GitHub API v3.