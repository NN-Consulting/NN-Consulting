using System;
using Spacia.Web.Models;

namespace Spacia.Web.Clases
{
    public class GrabarSesion
    {
        private static object theLocker = new object();
        private static LoginModel theData;
        public static LoginModel TheData
        {
                get
                {
                    lock (theLocker)
                    {
                        return theData;
                    }
                }
                set
                {
                    lock (theLocker)
                    {
                        theData = value;
                    }
                }
            }
        }
}
