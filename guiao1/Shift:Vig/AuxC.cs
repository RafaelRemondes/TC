using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TC_Dec
{
    class AuxC
    {
        /// <summary>
        /// Function responsible order a dictionary by descending
        /// </summary>
        /// <param name="dict">Dictionary</param>
        /// <returns>Dictionary ordered</returns>
        public static Dictionary<char, double> orderList(Dictionary<char, double> dict)
        {
            return dict.OrderByDescending(x => x.Value).ToDictionary(x => x.Key, x => x.Value);
        }

    }
}
