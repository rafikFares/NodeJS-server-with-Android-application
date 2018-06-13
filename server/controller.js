var mysql = require('mysql');

var mySqlClient = mysql.createConnection({
  host  :"localhost",
  user  :"root",
  password  :"",
  database  :"XXX"
});


exports.addData = function(req, res){
	console.log("données recus : ");
	console.log(req.body);
	var mName = req.body.name;
	var mAge = req.body.age;

	var insQuery = "INSERT INTO testJs (name, age) VALUES ('"+mName+"','"+mAge+"');";

	mySqlClient.query(insQuery, function select(error, results, fields) {

	    if (error) {
	      console.log(error);
	      mySqlClient.end();
	      return;
	    }
	    
    	res.send({message:"done", query:insQuery,result:results});

  	});
  	console.log("fin.");
};


exports.getData = function(req, res){
	console.log("Obtention des données ... ");
	var getQuery = "SELECT * FROM testJs;";

	mySqlClient.query(getQuery, function select(error, results, fields) {

	    if (error) {
	      console.log(error);
	      mySqlClient.end();
	      return;
	    }
	    
    	res.send({message:"done", query:getQuery, result:results});

  	});
  	console.log("fin.");
};


exports.getDataByField = function(req, res){
	console.log("Obtention des données pour un field ... ");
	console.log(req.query.age);
	var mAge = req.query.age;
	var getQuery = "SELECT * FROM testJs WHERE age ='"+mAge+"';";

	mySqlClient.query(getQuery, function select(error, results, fields) {

	    if (error) {
	      console.log(error);
	      mySqlClient.end();
	      return;
	    }
	    
    	res.send({message:"done", query:getQuery, result:results});

  	});
  	console.log("fin.");
};


exports.updateData = function(req, res){
	console.log("données recus pour mise a jour : ");
	console.log(req.body);
	//if i used @query in retrofit then the data will be in req.query.things
	var mId = req.body.id;
	var mName = req.body.name;
	var mAge = req.body.age;

	var upQuery = "UPDATE testJs SET name = '"+mName+"' , age = '"+mAge+"' WHERE id = '"+mId+"';";

	mySqlClient.query(upQuery, function select(error, results, fields) {

	    if (error) {
	      console.log(error);
	      mySqlClient.end();
	      return;
	    }
	    
    	res.send({message:"done", query:upQuery,result:results});

  	});
  	console.log("fin.");
};


exports.deleteData = function(req, res){
	console.log("Suppression en cours ... ");
	console.log(req.body);
	var mId = req.body.id;

	var delQuery = "DELETE FROM testJs WHERE id = '"+mId+"';";

	mySqlClient.query(delQuery, function select(error, results, fields) {

	    if (error) {
	      console.log(error);
	      mySqlClient.end();
	      return;
	    }
	    
    	res.send({message:"done", query:delQuery,result:results});

  	});
  	console.log("fin.");
};
