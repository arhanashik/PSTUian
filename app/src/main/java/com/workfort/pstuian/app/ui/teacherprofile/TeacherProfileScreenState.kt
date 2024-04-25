package com.workfort.pstuian.app.ui.teacherprofile

import com.workfort.pstuian.model.ProfileEditMode
import com.workfort.pstuian.model.TeacherProfile
import com.workfort.pstuian.model.UserType


data class TeacherProfileScreenState(
    val displayState: DisplayState = DisplayState.INITIAL,
    val navigationState: NavigationState? = null,
) {
    data class DisplayState(
        val selectedTabIndex: Int,
        val isSignedIn: Boolean,
        val profileState: ProfileState,
        val messageState: MessageState?,
    ) {
        companion object {
            val INITIAL = DisplayState(
                selectedTabIndex = 0,
                isSignedIn = false,
                profileState = ProfileState.None,
                messageState = null,
            )
        }
        sealed interface ProfileState {
            data object None : ProfileState
            data object Loading : ProfileState
            data class Available(val profile: TeacherProfile) : ProfileState
            data class Error(val message: String) : ProfileState
        }
        sealed interface MessageState {
            data class Loading(val cancelable: Boolean) : MessageState
            data class InputBio(val currentBio: String) : MessageState
            data class Call(val phoneNumber: String) : MessageState
            data class Email(val email: String) : MessageState
            data object ConfirmSignOut : MessageState
            data class Success(val message: String) : MessageState
            data class Error(val message: String) : MessageState
        }
    }

    sealed interface NavigationState {
        data object GoBack : NavigationState
        data class ImageUploadScreen(
            val userId: Int,
            val userType: UserType,
        ) : NavigationState
        data object ChangePasswordScreen : NavigationState
        data object MyDeviceListScreen : NavigationState
        data class TeacherProfileEditScreen(
            val userId: Int,
            val action: ProfileEditMode,
        ) : NavigationState
        data object DeleteAccountScreen : NavigationState
        data class ImagePreviewScreen(val encodedImageUrl: String) : NavigationState
    }
}