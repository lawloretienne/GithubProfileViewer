github-profile-viewer
=====================

Views profile information as well as received events of GitHub users using the GitHub API

Known Issues :

Since most likely the signature of the JSON Responses to API requests such as retreiving received events of a user has changed over time, I am properly handling only the more recent events.  To be more specific, there are particular event types like the 'CommitCommentEvent' which have changed in their signature from what they looked like a few years ago. 
