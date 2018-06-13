var express = require("express");
var bodyparser = require("body-parser");
var controllerExtern = require("./controller");

var app = express();

app.use(bodyparser.urlencoded({
	extended: true
}));

var router = express.Router();

app.use("/nodeApi", router);

router.route("/add").post(controllerExtern.addData);
router.route("/getdata").get(controllerExtern.getData);
router.route("/getfield").get(controllerExtern.getDataByField);
router.route("/update").put(controllerExtern.updateData);
router.route("/delete").delete(controllerExtern.deleteData);

app.listen(3000);



