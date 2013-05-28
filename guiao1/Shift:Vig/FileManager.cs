using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TC_Dec
{
    class FileManager
    {
        /// <summary>
        /// Function responsible for read a text file
        /// </summary>
        /// <param name="fileName">Name of the file</param>
        /// <returns>Content of file</returns>
        public static String getCripto(String fileName)
        {
            return File.ReadAllText(fileName);
        }

        /// <summary>
        /// Function responsible for read a file of char information
        /// </summary>
        /// <param name="path">path file</param>
        /// <returns>Dictionary with chars and frequency</returns>
        /// 
        public static Dictionary<char, double> readLanguageFile(String language)
        {
            String path = "..\\..\\FrequencyCharacter\\" + language + ".txt";
            Dictionary<char, double> chars = new Dictionary<char, double>();
            String line;
            char[] delimiterChars = { ' ', '-', '%' };
            char charR;
            double dbR;

            StreamReader fin = new StreamReader(path);

            try
            {
                //trash
                fin.ReadLine();
                fin.ReadLine();
                while ((line = fin.ReadLine()) != null)
                {
                    String[] words = line.Split(delimiterChars);
                    charR = words[0].ToCharArray(0, 1)[0];
                    dbR = Double.Parse(words[2].Replace('.', ','));

                    chars.Add(charR, dbR);
                }
            }
            catch (Exception e)
            {
                //TODO: catch exception
                return null;
            }
            finally
            {
                fin.Close();
            }

            return AuxC.orderList(chars);
        }

         /// <summary>
        /// Function responsible for read a file of words information
        /// </summary>
        /// <param name="path">path file</param>
        /// <returns>List with words more used</returns>
        public static List<String> readWordsFile(String path) {
            List<String> words = new List<String>();
            String line;

            StreamReader fin = new StreamReader(path);

            try {
                while ((line = fin.ReadLine()) != null) words.Add(line);

            } catch (Exception e) {
                return new List<String>();
            } finally {
                fin.Close();
            }

            return words;
        }

        /// <summary>
        /// Function responsible for write to a file
        /// </summary>
        /// <param name="text">text for write</param>
        /// <param name="fileName">File name</param>
        public static void writeToFile(String text, String fileName)
        {
            TextWriter fOut = new StreamWriter(fileName);

            fOut.Write(text);

            fOut.Close();
        }

    }
}
