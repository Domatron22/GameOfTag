package c.domatron.taggame

import android.arch.persistence.room.Room
import android.content.Context
import android.util.Log
import org.junit.*
import org.junit.rules.TestRule
import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.support.test.InstrumentationRegistry
import c.domatron.taggame.DAO.Player
import c.domatron.taggame.DAO.PlayerDao
import c.domatron.taggame.DAO.TagDatabase

/* Author: Dominic Triano
 * Date: 3/31/2019
 * Language: Kotlin
 * Project: TagGame
 * Description:
 *
 *
 */

class TagDaoTest {

    @Rule
    @JvmField
    val rule: TestRule = InstantTaskExecutorRule()

    private lateinit var database: TagDatabase
    private lateinit var PlayerDao: PlayerDao

    @Before
    fun setup() {
        val context: Context = InstrumentationRegistry.getTargetContext()
        try {
            database = Room.inMemoryDatabaseBuilder(context, TagDatabase::class.java)
                .allowMainThreadQueries().build()
        } catch (e: Exception) {
            Log.i("test", e.message)
        }
        PlayerDao = database.playerDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun testAddingAndRetrievingData() {
        val preInsertRetrievedCategories = PlayerDao.getAll()

        val players = Player(1, "Domatron", "", "password", 0)
        PlayerDao.insert(players)

        val postInsertRetrievedCategories = PlayerDao.getAll()
        val sizeDifference = postInsertRetrievedCategories.size - preInsertRetrievedCategories.size
        Assert.assertEquals(1, sizeDifference)
        val retrievedCategory = postInsertRetrievedCategories.last()
        Assert.assertEquals("Domatron", retrievedCategory.user)
    }
}