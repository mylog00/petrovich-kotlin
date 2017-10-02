package kt.petrovich

import org.junit.Test
import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.test.assertTrue


/**
 * @author Dmitrii Kniazev
 * @since 16.08.2017
 */
class PetrovichTest {
    @Test
    fun detectGenderTest() {
        val maleString = "Алексеевич"
        val femaleString = "Алексеевна"
        val wrongString = "блаблабла"

        assertTrue { Gender.detectGender(maleString) == Gender.MALE }
        assertTrue { Gender.detectGender(femaleString) == Gender.FEMALE }
        assertTrue { Gender.detectGender(wrongString) == Gender.ANDROGYNOUS }
    }

    @Test
    fun firstNameTest() {
        val p = Petrovich()
        val strings = loadTestSources("FirstNames.csv")

        for (line in strings) {
            if (line.size == 4) {
                val testName = line[0]
                val genderCase = Gender.valueOf(line[1].toUpperCase())
                val nameCase = Case.valueOf(line[2].toUpperCase())
                val assertName = line[3]

                val result = p.firstName(testName, genderCase, nameCase)
                System.out.printf("name=%s\tsex=%s\tcase=%s\texpected=%s\tresult=%s\n",
                        testName,
                        genderCase,
                        nameCase,
                        assertName,
                        result)
                assert(assertName == result)
            }
        }
    }

    @Test
    fun lastNameTest() {
        val p = Petrovich()
        val strings = loadTestSources("LastNames.csv")

        for (line in strings) {
            if (line.size == 4) {
                val testName = line[0]
                val genderCase = Gender.valueOf(line[1].toUpperCase())
                val nameCase = Case.valueOf(line[2].toUpperCase())
                val assertName = line[3]

                val result = p.lastName(testName, genderCase, nameCase)
                System.out.printf("name=%s\tsex=%s\tcase=%s\tresult=%s\n", testName, genderCase, nameCase, result)
                assert(assertName == result)
            }
        }
    }

    @Test
    fun middleNameTest() {
        val p = Petrovich()
        val strings = loadTestSources("MiddleNames.csv")

        for (line in strings) {
            if (line.size == 4) {
                val testName = line[0]
                val genderCase = Gender.valueOf(line[1].toUpperCase())
                val nameCase = Case.valueOf(line[2].toUpperCase())
                val assertName = line[3]

                val result = p.middleName(testName, genderCase, nameCase)
                val resultDetectGender = p.middleName(testName, nameCase)
                System.out.printf("name=%s\tsex=%s\tcase=%s\texpected=%s\tresult=%s\n",
                        testName,
                        genderCase,
                        nameCase,
                        assertName,
                        result)
                assert(assertName == result)
                assert(assertName == resultDetectGender)
            }
        }
    }

    private fun loadTestSources(path: String): Collection<List<String>> {
        val res = ArrayList<List<String>>()
        val classloader = Thread.currentThread().contextClassLoader

        classloader.getResourceAsStream(path).use {
            BufferedReader(InputStreamReader(it, "UTF-8")).use {
                var str: String? = it.readLine()
                while (str != null) {
                    val line: List<String> = str.replace(" ", "").split(",")
                    res.add(line)
                    str = it.readLine()
                }
            }
        }
        return res
    }
}
