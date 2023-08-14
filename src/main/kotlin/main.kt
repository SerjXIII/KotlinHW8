import java.text.SimpleDateFormat
import java.util.*

fun main() {
//    NoteService.add(Note("very interesting note", "First Note"))
//    NoteService.add(Note("very interesting note", "Second Note"))
//
//    NoteService.createComment(Comment("some comment", 1))
//    NoteService.createComment(Comment("some other comment", 1))
//
//    println(NoteService.getById(1))
//    println()
//    println(NoteService.getById(2))
//    println()
//    println(NoteService.getComments())
//    println()
//    println(NoteService.delete(1))
//    println(NoteService.delete(1))
//    println()
//    println(NoteService.deleteComment(1))
//    println(NoteService.getComments())
//
//    NoteService.add(Note("very interesting note", "Third Note"))
//    println(NoteService.getById(3))
//    println()
//    println(NoteService.edit(3,"new title", "new message", 0, 0))
//    println()
//    println(NoteService.getById(3))

//    NoteService.add(Note("very interesting note", "First Note"))
//    NoteService.add(Note("very interesting note", "First Note"))
//    NoteService.add(Note("very interesting note", "First Note"))
//    NoteService.add(Note("very interesting note", "First Note"))
//    NoteService.add(Note("very interesting note", "First Note"))
//    NoteService.add(Note("very interesting note", "First Note"))
//
//    println(NoteService.get("1,3,5"))
//    println(NoteService.getById(1))
//    NoteService.clear()
//    println(NoteService.getById(1))
//    println(NoteService.deleteComment(1))
//    println(NoteService.getComments())
//    println(NoteService.restoreComment(1))
//    println(NoteService.getComments())

}

object NoteService {
    private var notes = mutableListOf<Note>()
    private var comments = mutableListOf<Comment>()

    fun add(note: Note): Long {
        return if (notes.isEmpty()) {
            note.id = 1
            notes.add(note)
            note.id
        } else {
            note.id = notes.last().id + 1
            notes.add(note)
            note.id
        }
    }

    fun createComment(comment: Comment): Long {
        return if (comments.isEmpty()) {
            comment.id = 1
            comments.add(comment)
            comment.id
        } else {
            comment.id = comments.last().id + 1
            comments.add(comment)
            comment.id
        }
    }

    fun delete(noteId: Long): Int {
        for (note in notes) {
            if (note.id == noteId) {
                notes.remove(note)
                for (comment in comments) {
                    if (comment.noteId == noteId) {
                        comments.remove(comment)
                    }
                }
                return 1
            }
        }
        return 0
    }

    fun deleteComment(commentId: Long): Int {
        for (comment in comments) {
            if ((comment.id == commentId) && (!comment.isDeleted)) {
                comment.isDeleted = true
                return 1
            }
        }
        return 0
    }

    fun edit(
        noteId: Long,
        newTitle: String,
        newMessage: String,
        newPrivacy: Int,
        newCommentPrivacy: Int
    ): Int {
        for (note in notes) {
            if (note.id == noteId) {
                note.title = newTitle
                note.message = newMessage
                note.privacy = newPrivacy
                note.commentPrivacy = newCommentPrivacy
                return 1
            }
        }
        return 0
    }

    fun editComment(commentId: Long, newMessage: String): Int {
        for (comment in comments) {
            if ((comment.id == commentId) && (!comment.isDeleted)) {
                comment.message = newMessage
                return 1
            }
        }
        return 0
    }

    fun get(noteIds: String): MutableList<Note> {
        val listOfIds: List<String> = noteIds.split(",")
        val newNoteList = mutableListOf<Note>()
        for (id in listOfIds) {
            for (note in notes) {
                if (note.id == id.toLong()) {
                    newNoteList.add(note)
                }
            }
        }
        return newNoteList
    }

    fun getById(id: Long): Note? {
        for (note in notes) {
            if (note.id == id) return note
        }
        return null
    }

    fun getComments(): List<Comment> {
        val newCommentsList = mutableListOf<Comment>()
        for (comment in comments) {
            if (!comment.isDeleted) {
                newCommentsList.add(comment)
            }
        }
        return newCommentsList
    }


    fun restoreComment(commentId: Long): Int {
        for (comment in comments) {
            if ((comment.id == commentId) && (comment.isDeleted)) {
                comment.isDeleted = false
                return 1
            }
        }
        return 0
    }

    fun clear() {
        notes = mutableListOf<Note>()
        comments = mutableListOf<Comment>()
    }
}


data class Note(
    var message: String,
    var title: String,
    var privacy: Int = 0,
    var commentPrivacy: Int = 0
) {
    var id: Long = 0
    private val date: String = SimpleDateFormat("dd/M/yyyy hh:mm:ss").format(Date())

    override fun toString(): String {
        return "Note id - $id\nTitle - $title\nText of note - $message\nDate - $date\n"
    }
}

data class Comment(
    var message: String,
    var noteId: Long
) {
    var isDeleted: Boolean = false
    var id: Long = 0
    private val date: String = SimpleDateFormat("dd/M/yyyy hh:mm:ss").format(Date())

    override fun toString(): String {
        return """
Comment id - $id
Note ID - $noteId
Text of comment - $message
Date - $date 
"""
    }
}
