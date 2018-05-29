using System;
using System.Collections.Generic;

namespace Spacia.Web.Models
{
    public class AgendamientoModel
    {
        public string idEvento { get; set; }
        public string nroEvento { get; set; }
        public string descEvento { get; set; }

        public DataEventosModel data { get; set; }
    }
}
