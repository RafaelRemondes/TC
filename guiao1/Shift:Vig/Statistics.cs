using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TC_Dec
{
    class Statistics
    {
        private String chipher;
        private Dictionary<char,int> charFreq;

        public Statistics(String chipher)
        {
            this.chipher = chipher;
            this.charFreq = new Dictionary<char, int>();
        }

        public int length() { return this.charFreq.Count; }

        private void calcFreq() {

            foreach (char c in this.chipher)
                this.listEnc(c);

            //TODO: Sort dic
        }

        private void listEnc(char c) {
            if (this.charFreq.ContainsKey(c))
                this.charFreq[c]++;
            else this.charFreq.Add(c, 1);
        }

        public void showCharFreq()
        {
            if (this.charFreq.Count == 0) this.calcFreq();
            foreach(char c in this.charFreq.Keys)
                Console.WriteLine(c + " -> " + this.charFreq[c]);
        }

        public void showLanguageStats(String language) {
            Dictionary<char, double> langStat = FileManager.readLanguageFile(language);

            Console.WriteLine("######### " + language + " Statistcs #########");

            foreach(char c in langStat.Keys)
                Console.WriteLine(c + "->" + langStat[c]);

            Console.WriteLine("######### -------------- #########");
        }
    }
}
