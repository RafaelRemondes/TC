using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TC_Dec
{
    class Vigenere
    {
        private readonly string cipher;
        private String decipher;
        private String[] cipherBreak;
        private Dictionary<int, Dictionary<char, int>> freqByColun;

        private Dictionary<String, int> rep;
        private Dictionary<int, double> nDivisors;

        public Vigenere(String cipher) {
            this.cipher = cipher;
            this.decipher = this.cipher;
            this.rep = new Dictionary<string, int>();
            this.nDivisors = new Dictionary<int, double>();
            this.cipherBreak = null;
            this.freqByColun = new Dictionary<int, Dictionary<char, int>>();
        }

        public void showCipher()
        {
            Console.WriteLine("####### Cipher ######");
            Console.WriteLine(this.cipher);
            Console.WriteLine("####### ------ ######");
        }

        public void showRepStrings() {
            if (this.rep.Count == 0) this.startGetDiv();
            Console.WriteLine("##### Repeated Strings #####");

            foreach(String st in this.rep.Keys)
                Console.WriteLine(st + " -> " + this.rep[st]);

            Console.WriteLine("##### ---------------- #####");
        }

        public void showAllDivisorsFreq()
        {
            Console.WriteLine("##### Divisors #####");

            foreach (int div in this.nDivisors.Keys)
                Console.WriteLine(div + " -> " + this.nDivisors[div]);

            Console.WriteLine("##### --------- #####");
        }

        public void showFreqByColun() {
            Console.WriteLine("##### Freq by colunn #####");

            foreach (int col in this.freqByColun.Keys) {
                Console.WriteLine("## Colun " + col + " ##");
                foreach (char c in this.freqByColun[col].Keys)
                    Console.WriteLine(c + " -> " + this.freqByColun[col][c]);
            }

            Console.WriteLine("##### --------- #####");
        }

        public void breakCipher(int length) {
            String cipherToBreak = this.cipher;
            List<String> aux = new List<string>();
            while (cipherBreak != null) {
                aux.Add(cipherToBreak.Substring(0, length));
                cipherToBreak = cipherToBreak.Substring(length);
            }
            this.cipherBreak = aux.ToArray();
            Console.WriteLine("Chipher breaked");
            this.getColunFreq();
        }

        private void getColunFreq() {
            for (int i = 0; i < this.cipherBreak[0].Length; i++) {
                foreach (String s in this.cipherBreak)
                {
                    try
                    {
                        char c = s[i];
                    }
                    catch (Exception e) {
                        return;
                    }
                    this.addColunFreq(i, s[i]);
                }
            }
        }

        private void addColunFreq(int col, char c) {
            if (!this.freqByColun.ContainsKey(col)) {
                Dictionary<char, int> aux = new Dictionary<char, int>();
                aux.Add(c, 1);

                this.freqByColun.Add(col, aux);
                return;
            }
            Dictionary<char, int> aux2 = this.freqByColun[col];

            if (!aux2.ContainsKey(c))
            {
                aux2.Add(c, 1);
            }
            else {
                aux2[c]++;
            }
        }

        //-------- Get Divisores ------
        private void startGetDiv()
        {
            this.getRepDistances();
            this.getDivisors();
            this.convertDiv();
        }
        
        private void getRepDistances()
        {
            String testString = null, findString = null;
            int dist = 0;

            for (int length = 2; length < (this.cipher.Length / 2); length++)
            {
                for (int i = 0; i < (this.cipher.Length - length); i++)
                {
                    testString = this.cipher.Substring(i, length);
                    findString = this.cipher.Substring(i + length);
                    dist = length;

                    if (findString.Contains(testString))
                    {
                        while (!findString.StartsWith(testString))
                        {
                            findString = findString.Substring(1);
                            dist++;
                        }
                        if(!this.rep.ContainsKey(testString)) this.rep.Add(testString, dist);
                    }
                }
            }
        }

        private void getDivisors() {
            foreach (int dist in this.rep.Values)
                this.getDiv(dist);
        }

        private void getDiv(int number) {
            int max = (int)Math.Sqrt(number);  //round down
            for(int factor = 1; factor <= max; ++factor) { //test from 1 to the square root, or the int below it, inclusive.
                if(number % factor == 0) {
                    this.addDivisors(factor);
                    if(factor != number/factor) { // Don't add the square root twice!  Thanks Jon
                        this.addDivisors(number/factor);
                    }
                }
            }
        }

        private void addDivisors(int div) {
            if (this.nDivisors.ContainsKey(div))
                this.nDivisors[div]++;
            else this.nDivisors.Add(div, 1);
        }

        private void convertDiv() {
            int size = this.nDivisors.Count();
            Dictionary<int, double> aux = new Dictionary<int, double>();

            foreach(int k in this.nDivisors.Keys)
                aux.Add(k, this.nDivisors[k] / size);

            this.nDivisors = aux;
        }


        //-------- Get Divisores END ------
    }
}
