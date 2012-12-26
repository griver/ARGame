#pragma once

#include <iostream>
using std::cout;
using std::endl;
using std::istream;
using std::ostream;
using std::ios_base;

#include <fstream>
using std::ifstream;
using std::ofstream;

#include <vector>
using std::vector;

#include <string>
using std::string;
#include <sstream>

#include <boost/foreach.hpp>

#include <boost/shared_ptr.hpp>
using boost::shared_ptr;
#include <boost/make_shared.hpp>
using boost::make_shared;

//#include <boost/bind.hpp>

#include <set>
using std::set;

//#include <boost/date_time/posix_time/posix_time.hpp>
//using boost::posix_time::ptime;
//using boost::posix_time::microsec_clock;

#include <glm/glm.hpp>
#include <glm/gtc/matrix_access.hpp>
#include <glm/gtc/matrix_transform.hpp>
#include <glm/gtc/type_ptr.hpp>

#include <GLES2/gl2.h>
#include <GLES2/gl2ext.h>
#include <GLES2/gl2platform.h>

#include <jni.h>
#include <android/log.h>