using System;
using System.Collections.Generic;

namespace Spacia.Web.Models
{
    public class DetalleEventoModel
    {
        public int event_id { get; set; }
        public string name { get; set; }
        public AmbienteModel room { get; set; }
        public string date { get; set; }
        public string hour_from { get; set; }
        public string hour_to { get; set; }
        public int num_attendants { get; set; }
        public int num_resources { get; set; }
        public List<AsistentesModel> attendants { get; set; }
        public List<RecursosModel> resources { get; set; }
    }
}
