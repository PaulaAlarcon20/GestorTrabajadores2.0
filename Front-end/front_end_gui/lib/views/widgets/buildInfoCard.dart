
import 'package:flutter/material.dart';

Widget buildInfoCard(String title, String value){

  return Card(
    margin: const EdgeInsets.symmetric(vertical: 8),
    color: const Color.fromARGB(255, 113, 188, 223),
    elevation: 3,
    shape: RoundedRectangleBorder(
      borderRadius: BorderRadius.circular(12)
    ),
    child: Padding(
      padding: const EdgeInsets.all(10),
      child: Row(
        children: [
          //Icon(Icons.info_outline, color: Colors.blueAccent),
          //const SizedBox(width: 10),
          Expanded(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text(title, style: const TextStyle(fontWeight: FontWeight.bold, fontSize: 15)),
                //const SizedBox(width: 4),
                Text(value, style: TextStyle(fontSize: 17),)
              ],

            ),
          )

        ],
      ),)
      ,
  );

}