package com.workfort.pstuian.app.data.local.constant

import com.workfort.pstuian.BuildConfig
import com.workfort.pstuian.R

object Const {
    object RequestCode {
        const val LOCATION = 111
        const val IN_APP_UPDATE = 222
        const val PICK_IMAGE = 333
        const val PICK_PDF = 444
    }

    val backgroundList = arrayListOf(
        R.drawable.bg_gradient1,
        R.drawable.bg_gradient2,
        R.drawable.bg_gradient3,
        R.drawable.bg_gradient4,
        R.drawable.bg_gradient5,
        R.drawable.bg_gradient6,
        R.drawable.bg_gradient7,
        R.drawable.bg_gradient8,
        R.drawable.bg_gradient9,
    )

    object Key {
        const val FACULTY = "FACULTY"
        const val BATCH = "BATCH"
        const val STUDENT = "STUDENT"
        const val TEACHER = "TEACHER"
        const val EMPLOYEE = "EMPLOYEE"
        const val URL = "URL"
        const val URI = "URI"
        const val NAME = "NAME"
        const val MESSAGE = "MESSAGE"
        const val MAX_SIZE = "MAX_SIZE"
        const val PROGRESS = "PROGRESS"
        const val EDIT_ACTION = "EDIT_ACTION"
        const val EDIT_ACADEMIC = "EDIT_ACADEMIC"
        const val EDIT_CONNECT = "EDIT_CONNECT"
        const val UPDATED = "UPDATED"
        const val USER_TYPE = "USER_TYPE"
        const val EXTRA_IMAGE_TRANSITION_NAME = "EXTRA_IMAGE_TRANSITION_NAME"
    }

    object Params {
        const val ID = "id"
        const val OLD_ID = "old_id"
        const val USER_ID = "user_id"

        const val DEVICE_ID = "device_id"
        const val FCM_TOKEN = "fcm_token"
        const val MODEL = "model"
        const val ANDROID_VERSION = "android_version"
        const val APP_VERSION_CODE = "app_version_code"
        const val APP_VERSION_NAME = "app_version_name"
        const val IP_ADDRESS = "ip_address"
        const val LAT = "lat"
        const val LNG = "lng"
        const val LOCALE = "locale"

        const val EMAIL = "email"
        const val OLD_EMAIL = "old_email"
        const val PASSWORD = "password"
        const val OLD_PASSWORD = "old_password"
        const val NEW_PASSWORD = "new_password"
        const val USER_TYPE = "user_type"

        const val NAME = "name"
        const val REG = "reg"
        const val FACULTY_ID = "faculty_id"
        const val BATCH_ID = "batch_id"
        const val SESSION = "session"

        const val DESIGNATION = "designation"
        const val DEPARTMENT = "department"

        const val IMAGE_URL = "image_url"
        const val BIO = "bio"
        const val BLOOD = "blood"
        const val ADDRESS = "address"
        const val PHONE = "phone"
        const val CV_LINK = "cv_link"
        const val LINKED_IN = "linked_in"
        const val FB_LINK = "fb_link"

        const val CREATED_AT = "created_at"

        object UserType {
            const val STUDENT = "student"
            const val TEACHER = "teacher"
        }
    }

    object Remote {
        private const val LOCAL_SERVER = "http://192.168.1.100:8888/PSTUian-web/"
        private const val DEV_SERVER = "https://dev.pstuian.com/"
        private const val LIVE_SERVER = "https://pstuian.com/"
        private const val LOCAL_API_SERVER = "${LOCAL_SERVER}api/mobile/v1/"
        private const val DEV_API_SERVER = "https://api-dev.pstuian.com/mobile/v1/"
        private const val LIVE_API_SERVER = "https://api.pstuian.com/mobile/v1/"

        private val BASE_URL = if(BuildConfig.DEBUG) DEV_SERVER else LIVE_SERVER
        val BASE_API_URL = if(BuildConfig.DEBUG) DEV_API_SERVER else LIVE_API_SERVER
        val TERMS_AND_CONDITIONS = "${BASE_URL}terms_and_conditions.php"
        val PRIVACY_POLICY = "${BASE_URL}privacy_policy.php"
        val ADMISSION_SUPPORT = "${BASE_URL}admission_support.php"

        private const val CONFIG_API_PATH = "config.php?call="
        private const val DEVICE_API_PATH = "device.php?call="
        private const val AUTH_API_PATH = "auth.php?call="
        private const val STUDENT_API_PATH = "student.php?call="
        private const val TEACHER_API_PATH = "teacher.php?call="
        private const val NOTIFICATION_API_PATH = "notification.php?call="
        object Api {
            const val GET_CONFIG =  "${CONFIG_API_PATH}getLatest"
            const val REGISTER_DEVICE = "${DEVICE_API_PATH}register"
            const val UPDATE_FCM_TOKEN = "${DEVICE_API_PATH}updateFcmToken"

            object Auth {
                const val SIGN_IN = "${AUTH_API_PATH}signIn"
                const val SIGN_UP_STUDENT = "${AUTH_API_PATH}signUpStudent"
                const val SIGN_UP_TEACHER = "${AUTH_API_PATH}signUpTeacher"
                const val SIGN_OUT = "${AUTH_API_PATH}signOut"
                const val CHANGE_PASSWORD = "${AUTH_API_PATH}changePassword"
                const val FORGOT_PASSWORD = "${AUTH_API_PATH}forgotPassword"
            }

            object Student {
                const val CHANGE_PROFILE_IMAGE = "${STUDENT_API_PATH}updateImageUrl"
                const val UPDATE_NAME = "${STUDENT_API_PATH}updateName"
                const val UPDATE_BIO= "${STUDENT_API_PATH}updateBio"
                const val UPDATE_ACADEMIC_INFO= "${STUDENT_API_PATH}updateAcademicInfo"
                const val UPDATE_CONNECT_INFO= "${STUDENT_API_PATH}updateConnectInfo"
            }

            object Teacher {
                const val CHANGE_PROFILE_IMAGE = "${TEACHER_API_PATH}updateImageUrl"
                const val UPDATE_NAME = "${TEACHER_API_PATH}updateName"
                const val UPDATE_BIO= "${TEACHER_API_PATH}updateBio"
                const val UPDATE_ACADEMIC_INFO= "${TEACHER_API_PATH}updateAcademicInfo"
                const val UPDATE_CONNECT_INFO= "${TEACHER_API_PATH}updateConnectInfo"
            }

            object Notification {
                const val GET_ALL = "${NOTIFICATION_API_PATH}getAll"
            }
        }
    }

    object Fcm {
        object DataKey {
            const val TYPE ="type"
            const val TITLE ="title"
            const val MESSAGE ="message"
            const val ACTION ="action"
        }
    }

    object IntentAction {
        private const val APP_ID = BuildConfig.APPLICATION_ID
        const val NOTIFICATION = "${APP_ID}.NOTIFICATION"
    }

    object NotificationType {
        const val DEFAULT = "default"
        const val BLOOD_DONATION = "blood_donation"
        const val NEWS = "news"
        const val HELP = "help"
    }
}