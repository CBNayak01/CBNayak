import'package:flutter/material.dart';
void main() {
  runApp(const MyApp());
}
class MyApp extends StatelessWidget {
const MyApp({super.key});
//@Override
Widget build(BuildContext context) {
return MaterialApp(
title:'Navigation Example',
theme:ThemeData(
primarySwatch: Colors.blue,
visualDensity: VisualDensity.adaptivePlatformDensity,
),
home: const FirstPage()
);
}
}
class FirstPage extends StatelessWidget {
  const FirstPage({super.key});
  //@Override
  Widget build(BuildContext context){
    return Scaffold(
      appBar:AppBar(title:const Text("First Page")),
      body:Center(
        child: SizedBox(
        width:double.infinity,
          child:Padding(
          padding: const EdgeInsets.symmetric(horizontal:20.0),
          child:ElevatedButton(
            onPressed:(){
              Navigator.push(
                context,
                MaterialPageRoute<void>(builder:(BuildContext context)=>const FirstPage()),
              );
            },
            child:const Text("Click here to navigate"),
            ),
          ),
        ),
      ),
    );
  }
}
class SecondPage extends StatelessWidget{
  const SecondPage({super.key});
  // @Override
  Widget build(BuildContext context){
    return Scaffold(
      appBar:AppBar(title:const Text("Second page")),
      body:Center(
        child: SizedBox(
        width:double.infinity,
          child:Padding(
          padding: const EdgeInsets.symmetric(horizontal:20.0),
          child:ElevatedButton(
            onPressed:(){
                  Navigator.pop(context);//Go back to the First page
            },
            child:const Text("Go back to First page"),
            ),
          ),
        ),
      ),
    );
  }
}
   