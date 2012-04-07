import weka.attributeSelection.*;
import weka.core.*;
import weka.core.converters.ConverterUtils.*;
import weka.classifiers.*;
import weka.classifiers.meta.*;
import weka.classifiers.trees.*;
import weka.filters.*;

import java.util.*;

/**
 * A template file for COMP 382 Assignment 1
 * @author Onur Güngör
 */
public class DecisionTree {

    static AttributeStats stats;

    static RandomVariates random;

    static int decideClass(Instance sample) {
/**
 * Added decide class 
 * @author Sezgi Sensoz
*/
	Instances data = sample.dataset();

	double attr_value_1 = -1000;
	String attr_string_value_1 = new String();

	double attr_value_2 = -1000;
	String attr_string_value_2 = new String();
	
	int aa= 1;
	Attribute attr1 = sample.attribute(aa);
	int bb=10;
	Attribute attr2 = sample.attribute(bb);

	if (attr1.type() == Attribute.STRING) {
	    attr_value_1 = sample.value(aa);
	    attr_string_value_1 = attr1.value((int)attr_value_1);
	} else {
	    attr_value_1 = sample.value(aa);
	}

	if (attr2.type() == Attribute.STRING) {
	    attr_value_2 = sample.value(bb);
	    attr_string_value_2 = attr2.value((int)attr_value_2);
	} else {
	    attr_value_2 = sample.value(bb);
	}

	if (attr_value_1 > 2.5) {
		if(attr_value_2 >10)
			return 1; // good
		else return 0; 
	} else {
	    return 0; // bad
	}

	// int nClasses = stats.distinctCount;
	// int[] classCounts = new int[nClasses];
	// classCounts = stats.nominalCounts;

	// // always answer with the class id of the most frequent class
	// if (classCounts[0] > classCounts[1]) {
	//     return 0;
	// } else {
	//     return 1;
	// }

	// // a worser but "fairer" alternative
	// // float randomFloat = random.nextFloat();

	// // if (randomFloat < (float)classCounts[0]/stats.totalCount) {
	// //     return 0;
	// //     //	    return classNominalValues[0];
	// // } else {
	// //     return 1;
	// //     //	    return classNominalValues[1];
	// // }
    }

    static int runForAllTheInstances(Instances data) {

	int correctPredictions = 0;
	
	Enumeration dataEnum = data.enumerateInstances();

	while (dataEnum.hasMoreElements()) {
	    Instance sample = (Instance)dataEnum.nextElement();
	    int classId = decideClass(sample);
	    System.out.println(sample);
	    System.out.println(classId+"");

	    if (classId == (int)sample.classValue()) {
		correctPredictions += 1;
	    }
	}

	return correctPredictions;
    }

    /**
     * takes a dataset as first argument
     *
     * @param args        the commandline arguments
     * @throws Exception  if something goes wrong
     */
    public static void main(String[] args) throws Exception {
	// load data
	System.out.println("\n0. Loading data");
	DataSource source = new DataSource(args[0]);
	Instances data = source.getDataSet();
	if (data.classIndex() == -1) {
	    data.setClassIndex(data.numAttributes() - 1);
	}
	
	stats = data.attributeStats(data.classIndex());

	random = new RandomVariates();

	int correctPredictions = 0;
	int nBatches = 10;

	for (int i = 0; i < nBatches; i++) {
	    correctPredictions += runForAllTheInstances(data);
	}
	
	float TPpercentage = (float)correctPredictions/(nBatches*data.numInstances());

	System.out.println("True Positive Percentage: "+TPpercentage);
    }
}
