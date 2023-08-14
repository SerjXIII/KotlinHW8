import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class NoteServiceTest {
    @Before
    fun clearBeforeTest() {
        NoteService.clear()
    }

    @Test
    fun addTest1() {
        NoteService.add(Note("very interesting note", "First Note"))
        val x = (NoteService.getById(1))?.id
        if (x != null) {
            assertEquals(1, x.toInt())
        }
    }

    @Test
    fun addTest2() {
        NoteService.add(Note("very interesting note", "First Note"))
        NoteService.add(Note("very interesting note", "Second Note"))
        val x = (NoteService.getById(2))?.id
        if (x != null) {
            assertEquals(2, x.toInt())
        }
    }

    @Test
    fun createComment1() {
        NoteService.add(Note("very interesting note", "First Note"))
        NoteService.createComment(Comment("some comment", 1))
        assertEquals(1, (((NoteService.getComments())[0].id).toInt()))
    }

    @Test
    fun createComment2() {
        NoteService.add(Note("very interesting note", "First Note"))
        NoteService.createComment(Comment("some comment", 1))
        NoteService.createComment(Comment("some other comment", 1))
        assertEquals(2, (((NoteService.getComments())[1].id).toInt()))
    }

    @Test
    fun deleteNote1() {
        NoteService.add(Note("very interesting note", "First Note"))
        NoteService.add(Note("very interesting note", "Second Note"))
        assertEquals(1, NoteService.delete(2))
    }

    @Test
    fun deleteNote2() {
        assertEquals(0, NoteService.delete(1))
    }

    @Test
    fun deleteComment1() {
        NoteService.add(Note("very interesting note", "First Note"))
        NoteService.createComment(Comment("some comment", 1))
        NoteService.createComment(Comment("some comment", 1))
        assertEquals(1, NoteService.deleteComment(2))
    }

    @Test
    fun deleteComment2() {
        assertEquals(0, NoteService.deleteComment(1))
    }

    @Test
    fun editTest1() {
        NoteService.add(Note("very interesting note", "First Note"))
        NoteService.edit(1, "New Title", "mes", 1, 1)

        assertEquals("New Title", NoteService.getById(1)?.title ?: "First Note")
    }

    @Test
    fun editTest2() {
        NoteService.add(Note("very interesting note", "First Note"))

        assertEquals(1, NoteService.edit(1, "New Title", "mes", 1, 1))
    }

    @Test
    fun editTest3() {
        NoteService.edit(1, "New Title", "mes", 1, 1)

        assertEquals(0, NoteService.edit(1, "New Title", "mes", 1, 1))
    }

    @Test
    fun editComment1() {
        NoteService.add(Note("very interesting note", "First Note"))
        NoteService.createComment(Comment("some comment", 1))
        assertEquals(1, NoteService.editComment(1, "Some other comment"))
    }

    @Test
    fun editComment2() {
        assertEquals(0, NoteService.editComment(1, "Some other comment"))
    }

    @Test
    fun get1() {
        NoteService.add(Note("very interesting note", "First Note"))
        NoteService.add(Note("very interesting note", "Second Note"))
        NoteService.add(Note("very interesting note", "Third Note"))
        assertEquals(2, (NoteService.get("1,2")).size)
    }

    @Test
    fun get2() {
        NoteService.add(Note("very interesting note", "First Note"))
        NoteService.add(Note("very interesting note", "Second Note"))
        NoteService.add(Note("very interesting note", "Third Note"))
        assertEquals(0, (NoteService.get("4,5")).size)
    }

    @Test
    fun getById1() {
        NoteService.add(Note("very interesting note", "First Note"))

        NoteService.getById(1)?.let { assertEquals(1, it.id) }
    }

    @Test
    fun getById2() {
        assertNull(NoteService.getById(1))
    }

    @Test
    fun getComment1() {
        NoteService.add(Note("very interesting note", "First Note"))
        NoteService.createComment(Comment("some comment", 1))
        NoteService.createComment(Comment("some comment", 1))
        NoteService.createComment(Comment("some comment", 1))

        assertEquals(3, (NoteService.getComments()).size)
    }

    @Test
    fun getComment2() {
        assertEquals(0, (NoteService.getComments()).size)
    }

    @Test
    fun restoreComment1() {
        NoteService.add(Note("very interesting note", "First Note"))
        NoteService.createComment(Comment("some comment", 1))
        NoteService.deleteComment(1)
        assertEquals(1, NoteService.restoreComment(1))
    }

    @Test
    fun restoreComment2() {
        NoteService.add(Note("very interesting note", "First Note"))
        NoteService.createComment(Comment("some comment", 1))

        assertEquals(0, NoteService.restoreComment(1))
    }
}