using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TC_Dec
{
    class ComandParser
    {
        Replace replace;
        Statistics st;
        Vigenere vg;

        public ComandParser(String cipher) {
            this.replace = new Replace(cipher);
            this.st = new Statistics(cipher);
            this.vg = new Vigenere(cipher);
        }

        public void showDecipher(Boolean b) {
            if (b) replace.showDecipher();
            if (!b) vg.showCipher();
        }

        private void globalOptions() {
            Console.WriteLine();
            Console.WriteLine("s1 - show cipher char statistics");
            Console.WriteLine("s2 - show English statistics");
            Console.WriteLine("s3 - show French statistics");
            Console.WriteLine("exit - exit");
            Console.WriteLine("######## ------- #######");
            Console.WriteLine();
        }

        public void showReplaceOptions() {
            Console.WriteLine();
            Console.WriteLine();
            Console.WriteLine("######## REPLACE OPTIONS #######");
            Console.WriteLine("r1 - show cipher");
            Console.WriteLine("r2 - show decipher");
            Console.WriteLine("r3 - show replaced chars");
            Console.WriteLine("r4 - reset decipher");
            Console.WriteLine("r5 - replace char");
            Console.WriteLine("r6 - get shift");
            Console.WriteLine("r7 - Auto Shitf");
            this.globalOptions();
        }

        public void showVigenereOptions() {
            Console.WriteLine();
            Console.WriteLine();
            Console.WriteLine("######## VIGENERE OPTIONS #######");
            Console.WriteLine("v1 - show cipher");
            Console.WriteLine("v2 - show repeat Strings");
            Console.WriteLine("v3 - show divisors");
            Console.WriteLine("v4 - show Freq by colun");
            Console.WriteLine("v5 - break string");
            this.globalOptions();
        }

        public void replaceCharParser() {
            Console.Write("Source char:");
            String source = Console.ReadLine();
            Console.WriteLine();
            Console.Write("Dest char:");
            String dest = Console.ReadLine();
            Console.WriteLine();
            this.replace.replace(source[0], dest[0]);
        }

        public void parseComand(string c) {
            if (c.Trim().StartsWith("r1")) this.replace.showCipher();
            if (c.Trim().StartsWith("r2")) this.replace.showDecipher();
            if (c.Trim().StartsWith("r3")) this.replace.showAllReplaces();
            if (c.Trim().StartsWith("r4")) this.replace.resetDecipher();
            if (c.Trim().StartsWith("r5")) this.replaceCharParser();
            if (c.Trim().StartsWith("r6")) 
            {
                Console.Write("Source: ");
                String s = Console.ReadLine();
                Console.Write("Dest: ");
                String d = Console.ReadLine();
                this.replace.calcShift(s[0], d[0]);
            }
            if (c.Trim().StartsWith("r7"))
            {
                Console.Write("Shift Length: ");
                String sh = Console.ReadLine();
                this.replace.shiftCharCipher(Convert.ToInt32(sh));
            }

            if (c.Trim().StartsWith("s1")) this.st.showCharFreq();
            if (c.Trim().StartsWith("s2")) this.st.showLanguageStats("English");
            if (c.Trim().StartsWith("s3")) this.st.showLanguageStats("French");
            if (c.Trim().StartsWith("exit")) Environment.Exit(0);

            if (c.Trim().StartsWith("v1")) this.vg.showCipher();
            if (c.Trim().StartsWith("v2")) this.vg.showRepStrings();
            if (c.Trim().StartsWith("v3")) this.vg.showAllDivisorsFreq();
            if (c.Trim().StartsWith("v4")) this.vg.showFreqByColun();

            if (c.Trim().StartsWith("v5")) {
                Console.Write("Break Length: ");
                String sh = Console.ReadLine();
                this.vg.breakCipher(Convert.ToInt32(sh));
            }
        }
    }
}
