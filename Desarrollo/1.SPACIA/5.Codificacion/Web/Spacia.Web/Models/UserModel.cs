using System;
using System.Collections.Generic;
using System.Linq;
using Newtonsoft.Json.Linq;

namespace Spacia.Web.Models
{
    public class UserModel
    {
        public UserModel(string json)
        {
            JObject jObject = JObject.Parse(json);
            JToken jUser = jObject["user"];
            id = (int) jUser["id"];
            /*teamname = (string) jUser["teamname"];
            email = (string) jUser["email"];
            players = jUser["players"].ToArray();*/
        }

        public int id { get; set; }
        /*public string teamname { get; set; }
        public string email { get; set; }
        public Array players { get; set; }*/
    }
}
