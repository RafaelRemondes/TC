using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TC_Dec
{
    class Replace
    {
        private readonly String cipher;
        private String decipher;
        //Dic<InitChar, NewChar>
        private Dictionary<char, char> replacedChars;

        public Replace(String cipher) {
            this.cipher = cipher;
            this.decipher = this.cipher;
            this.replacedChars = new Dictionary<char, char>();
        }

        public String replace(char origChar, char replaceChar) {
            this.decipher = this.decipher.Replace(origChar, replaceChar);
            this.replacedChars.Add(origChar, replaceChar);
            return this.decipher;
        }

        public void showAllReplaces() {
            Console.WriteLine("######### Replaced Chars #########");

            foreach (char c in this.replacedChars.Keys)
                Console.WriteLine(c + "->" + this.replacedChars[c]);

            Console.WriteLine("######### -------------- #########");
        }

        public void calcShift(char cipherC, char langC) {
            Console.WriteLine("Shift Lengh = " + (langC - cipherC));
        }

        public void resetDecipher()
        {
            this.decipher = this.cipher;
            Console.WriteLine("Possible cleartext reseted");
        }

        public void showCipher()
        {
            Console.WriteLine("####### Cipher ######");
            Console.WriteLine(this.cipher);
            Console.WriteLine("####### ------ ######");
        }

        public void showDecipher()
        {
            Console.WriteLine("####### Decipher ######");
            Console.WriteLine(this.decipher);
            Console.WriteLine("####### ------ ######");
        }

        public void shiftCharCipher(int shift)
        {
            char[] txt = this.cipher.ToCharArray(0, this.cipher.Length);

            for (int i = 0; i < this.cipher.Length; i++)
            {
                int letter = txt[i] + shift;

                //FIXME: if is a short alphafet
                if (letter < 65) letter += 26;
                if (letter > 90) letter -= 26;

                txt[i] = (char)letter;
            }
            this.decipher = new String(txt);
        }
    }
}
