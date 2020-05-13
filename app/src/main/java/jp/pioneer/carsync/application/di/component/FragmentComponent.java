package jp.pioneer.carsync.application.di.component;

import dagger.Subcomponent;
import jp.pioneer.carsync.application.di.FragmentLifeCycle;
import jp.pioneer.carsync.application.di.module.FragmentModule;
import jp.pioneer.carsync.presentation.view.fragment.dialog.AppConnectMethodDialogFragment;
import jp.pioneer.carsync.presentation.view.fragment.dialog.CustomKeySettingDialogFragment;
import jp.pioneer.carsync.presentation.view.fragment.dialog.VoiceRecognizeTypeSelectDialogFragment;
import jp.pioneer.carsync.presentation.view.fragment.dialog.YouTubeLinkCautionFragment;
import jp.pioneer.carsync.presentation.view.fragment.dialog.YouTubeLinkContainerFragment;
import jp.pioneer.carsync.presentation.view.fragment.dialog.YouTubeLinkSearchItemDialogFragment;
import jp.pioneer.carsync.presentation.view.fragment.dialog.YouTubeLinkWebViewFragment;
import jp.pioneer.carsync.presentation.view.fragment.preference.HdRadioSettingFragment;
import jp.pioneer.carsync.presentation.view.fragment.dialog.AccidentDetectDialogFragment;
import jp.pioneer.carsync.presentation.view.fragment.dialog.AdasWarningDialogFragment;
import jp.pioneer.carsync.presentation.view.fragment.dialog.AlexaFragment;
import jp.pioneer.carsync.presentation.view.fragment.dialog.BackgroundImagePreviewFragment;
import jp.pioneer.carsync.presentation.view.fragment.dialog.CautionDialogFragment;
import jp.pioneer.carsync.presentation.view.fragment.dialog.GuidanceVolumeDialogFragment;
import jp.pioneer.carsync.presentation.view.fragment.dialog.LocalDialogFragment;
import jp.pioneer.carsync.presentation.view.fragment.dialog.LoudnessDialogFragment;
import jp.pioneer.carsync.presentation.view.fragment.dialog.MenuDisplayLanguageDialogFragment;
import jp.pioneer.carsync.presentation.view.fragment.dialog.PairingSelectDialogFragment;
import jp.pioneer.carsync.presentation.view.fragment.dialog.ParkingSensorDialogFragment;
import jp.pioneer.carsync.presentation.view.fragment.dialog.PromptAuthorityPermissionDialogFragment;
import jp.pioneer.carsync.presentation.view.fragment.dialog.ReadingMessageDialogFragment;
import jp.pioneer.carsync.presentation.view.fragment.dialog.SessionStoppedDialogFragment;
import jp.pioneer.carsync.presentation.view.fragment.dialog.SingleChoiceDialogFragment;
import jp.pioneer.carsync.presentation.view.fragment.dialog.SpeechRecognizerDialogFragment;
import jp.pioneer.carsync.presentation.view.fragment.dialog.StatusPopupDialogFragment;
import jp.pioneer.carsync.presentation.view.fragment.dialog.VideoPlayerDialogFragment;
import jp.pioneer.carsync.presentation.view.fragment.preference.AdasCameraPositionSettingFragment;
import jp.pioneer.carsync.presentation.view.fragment.preference.AdasSettingFragment;
import jp.pioneer.carsync.presentation.view.fragment.preference.AdasWarningSettingFragment;
import jp.pioneer.carsync.presentation.view.fragment.preference.AppSettingFragment;
import jp.pioneer.carsync.presentation.view.fragment.preference.AudioFragment;
import jp.pioneer.carsync.presentation.view.fragment.preference.CarSafetyFragment;
import jp.pioneer.carsync.presentation.view.fragment.preference.DabSettingFragment;
import jp.pioneer.carsync.presentation.view.fragment.preference.DebugSettingFragment;
import jp.pioneer.carsync.presentation.view.fragment.preference.EulaFragment;
import jp.pioneer.carsync.presentation.view.fragment.preference.FxFragment;
import jp.pioneer.carsync.presentation.view.fragment.preference.ImpactDetectionSettingsFragment;
import jp.pioneer.carsync.presentation.view.fragment.preference.InformationFragment;
import jp.pioneer.carsync.presentation.view.fragment.preference.InitialSettingFragment;
import jp.pioneer.carsync.presentation.view.fragment.preference.KaraokeFragment;
import jp.pioneer.carsync.presentation.view.fragment.preference.LicenseFragment;
import jp.pioneer.carsync.presentation.view.fragment.preference.MessageFragment;
import jp.pioneer.carsync.presentation.view.fragment.preference.NavigationFragment;
import jp.pioneer.carsync.presentation.view.fragment.preference.ParkingSensorFragment;
import jp.pioneer.carsync.presentation.view.fragment.preference.PhoneSettingFragment;
import jp.pioneer.carsync.presentation.view.fragment.preference.PrivacyPolicyFragment;
import jp.pioneer.carsync.presentation.view.fragment.preference.RadioFunctionSettingFragment;
import jp.pioneer.carsync.presentation.view.fragment.preference.SystemFragment;
import jp.pioneer.carsync.presentation.view.fragment.preference.ThemeFragment;
import jp.pioneer.carsync.presentation.view.fragment.preference.VoiceSettingFragment;
import jp.pioneer.carsync.presentation.view.fragment.preference.YouTubeLinkSettingFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.contacts.ContactsContainerFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.contacts.ContactsFavoriteFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.contacts.ContactsFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.contacts.ContactsHistoryFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.home.HomeContainerFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.home.HomeFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.home.OpeningEulaFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.home.OpeningFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.home.OpeningPrivacyPolicyFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.player.AndroidMusicFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.player.AuxFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.player.BtAudioFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.player.CdFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.player.DabFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.player.HdRadioFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.player.PandoraFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.player.PlayerContainerFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.player.RadioFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.player.SourceAppSettingFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.player.SourceOffFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.player.SourceSelectContainerFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.player.SourceSelectFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.player.SpotifyFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.player.SxmFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.player.TiFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.player.UnsupportedFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.player.UsbFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.player.list.AlbumSongsFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.player.list.AlbumsFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.player.list.ArtistAlbumSongsFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.player.list.ArtistAlbumsFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.player.list.ArtistsFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.player.list.DabEnsembleListFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.player.list.DabPtyListFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.player.list.DabServiceListFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.player.list.GenreSongsFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.player.list.GenresFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.player.list.NowPlayingListContainerFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.player.list.NowPlayingListFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.player.list.PlayerTabContainerFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.player.list.PlaylistSongsFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.player.list.PlaylistsFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.player.list.RadioFavoriteFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.player.list.RadioPresetFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.player.list.RadioTabContainerFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.player.list.SongsFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.player.list.UsbListContainerFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.player.list.UsbListFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.search.SearchAlbumSongsFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.search.SearchArtistAlbumSongsFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.search.SearchArtistAlbumsFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.search.SearchContactFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.search.SearchContainerFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.search.SearchMusicFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.settings.AdasBillingFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.settings.AdasCalibrationSettingFittingFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.settings.AdasCalibrationSettingFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.settings.AdasManualFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.settings.AdasTutorialFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.settings.AdasUsageCautionFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.settings.AdvancedAudioSettingFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.settings.AlexaExampleUsageFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.settings.AlexaSettingFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.settings.AlexaSplashFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.settings.BtDeviceListFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.settings.BtDeviceSearchFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.settings.ClassicBtDeviceListFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.settings.CrossOverSettingsFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.settings.DimmerSettingFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.settings.DirectCallContactSettingFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.settings.DirectCallSettingFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.settings.EqProSettingFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.settings.EqProSettingZoomFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.settings.EqQuickSettingFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.settings.EqSettingFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.settings.FaderBalanceSettingFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.settings.IlluminationColorSettingFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.settings.ImpactDetectionContactRegisterFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.settings.ImpactDetectionContactSettingFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.settings.IncomingCallColorFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.settings.IncomingCallPatternFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.settings.IncomingMessageColorFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.settings.LiveSimulationSettingFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.settings.SettingsContainerFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.settings.SettingsEntranceFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.settings.SmallCarTaSettingFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.settings.ThemeSetFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.settings.TodorokiSettingFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.settings.UiColorSettingFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.unconnected.EasyPairingFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.unconnected.TipsFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.unconnected.TipsWebFragment;
import jp.pioneer.carsync.presentation.view.fragment.screen.unconnected.UnconnectedContainerFragment;

/**
 * Fragment用のDaggerコンポーネント.
 */
@FragmentLifeCycle
@Subcomponent(modules = FragmentModule.class)
public interface FragmentComponent {
    // DIALOG
    void inject(SingleChoiceDialogFragment fragment);
    void inject(StatusPopupDialogFragment fragment);
    // HOME
    void inject(HomeContainerFragment fragment);
    void inject(HomeFragment fragment);
    void inject(OpeningFragment fragment);
    void inject(OpeningEulaFragment fragment);
    void inject(AccidentDetectDialogFragment fragment);
    void inject(ParkingSensorDialogFragment fragment);
    void inject(OpeningPrivacyPolicyFragment fragment);
    void inject(CautionDialogFragment fragment);
    void inject(SessionStoppedDialogFragment fragment);
    void inject(AdasWarningDialogFragment fragment);
    void inject(CustomKeySettingDialogFragment fragment);
    void inject(YouTubeLinkContainerFragment fragment);
    void inject(YouTubeLinkCautionFragment fragment);
    void inject(YouTubeLinkWebViewFragment fragment);
    void inject(YouTubeLinkSearchItemDialogFragment fragment);
    void inject(VoiceRecognizeTypeSelectDialogFragment fragment);
    // UNCONNECTED
    void inject(UnconnectedContainerFragment fragment);
    void inject(TipsFragment fragment);
    void inject(EasyPairingFragment fragment);
    void inject(PairingSelectDialogFragment fragment);
    void inject(TipsWebFragment fragment);
    // PLAYER
    void inject(PlayerContainerFragment fragment);
    void inject(SourceSelectContainerFragment fragment);
    void inject(SourceSelectFragment fragment);
    void inject(SourceAppSettingFragment fragment);
    void inject(RadioFragment fragment);
    void inject(RadioTabContainerFragment fragment);
    void inject(ReadingMessageDialogFragment fragment);
    void inject(RadioPresetFragment fragment);
    void inject(RadioFavoriteFragment fragment);
    void inject(DabFragment fragment);
    void inject(DabServiceListFragment fragment);
    void inject(DabPtyListFragment fragment);
    void inject(DabEnsembleListFragment fragment);
    void inject(HdRadioFragment fragment);
    void inject(SxmFragment fragment);
    void inject(AndroidMusicFragment fragment);
    void inject(NowPlayingListContainerFragment fragment);
    void inject(NowPlayingListFragment fragment);
    void inject(CdFragment fragment);
    void inject(UsbFragment fragment);
    void inject(UsbListContainerFragment fragment);
    void inject(UsbListFragment fragment);
    void inject(PlayerTabContainerFragment fragment);
    void inject(ArtistsFragment fragment);
    void inject(ArtistAlbumsFragment fragment);
    void inject(ArtistAlbumSongsFragment fragment);
    void inject(AlbumsFragment fragment);
    void inject(AlbumSongsFragment fragment);
    void inject(SongsFragment fragment);
    void inject(PlaylistsFragment fragment);
    void inject(PlaylistSongsFragment fragment);
    void inject(GenresFragment fragment);
    void inject(GenreSongsFragment fragment);
    void inject(AuxFragment fragment);
    void inject(UnsupportedFragment fragment);
	void inject(SpotifyFragment fragment);
    void inject(PandoraFragment fragment);
    void inject(BtAudioFragment fragment);
    void inject(SourceOffFragment fragment);
    void inject(TiFragment fragment);
    // CONTACTS
    void inject(ContactsContainerFragment fragment);
    void inject(ContactsFragment fragment);
    void inject(ContactsHistoryFragment fragment);
    void inject(ContactsFavoriteFragment fragment);
    // SEARCH
    void inject(SpeechRecognizerDialogFragment fragment);
    void inject(SearchContainerFragment fragment);
    void inject(SearchContactFragment fragment);
    void inject(SearchMusicFragment fragment);
    void inject(SearchArtistAlbumsFragment fragment);
    void inject(SearchArtistAlbumSongsFragment fragment);
    void inject(SearchAlbumSongsFragment fragment);
    // SETTINGS
    void inject(SettingsEntranceFragment fragment);
    void inject(SettingsContainerFragment fragment);
    void inject(SystemFragment fragment);
    void inject(ThemeFragment fragment);
    void inject(ThemeSetFragment fragment);
    void inject(IlluminationColorSettingFragment fragment);
    void inject(UiColorSettingFragment fragment);
    void inject(DimmerSettingFragment fragment);
    void inject(FxFragment fragment);
    void inject(EqSettingFragment fragment);
    void inject(EqQuickSettingFragment fragment);
    void inject(EqProSettingFragment fragment);
    void inject(EqProSettingZoomFragment fragment);
    void inject(LiveSimulationSettingFragment fragment);
    void inject(TodorokiSettingFragment fragment);
    void inject(SmallCarTaSettingFragment fragment);
    void inject(KaraokeFragment fragment);
    void inject(AudioFragment fragment);
    void inject(LoudnessDialogFragment fragment);
    void inject(AppSettingFragment fragment);
    void inject(NavigationFragment fragment);
    void inject(GuidanceVolumeDialogFragment fragment);
    void inject(MessageFragment fragment);
    void inject(CarSafetyFragment fragment);
    void inject(ImpactDetectionSettingsFragment fragment);
    void inject(ImpactDetectionContactSettingFragment fragment);
    void inject(ImpactDetectionContactRegisterFragment fragment);
    void inject(DirectCallSettingFragment fragment);
    void inject(DirectCallContactSettingFragment fragment);
    void inject(InformationFragment fragment);
    void inject(LicenseFragment fragment);
    void inject(EulaFragment fragment);
    void inject(PrivacyPolicyFragment fragment);
    void inject(AdvancedAudioSettingFragment fragment);
    void inject(CrossOverSettingsFragment fragment);
    void inject(FaderBalanceSettingFragment fragment);
    void inject(RadioFunctionSettingFragment fragment);
    void inject(DabSettingFragment fragment);
    void inject(HdRadioSettingFragment fragment);
    void inject(LocalDialogFragment fragment);
    void inject(VoiceSettingFragment fragment);
    void inject(InitialSettingFragment fragment);
    void inject(MenuDisplayLanguageDialogFragment fragment);
    void inject(PhoneSettingFragment fragment);
    void inject(BtDeviceListFragment fragment);
    void inject(BtDeviceSearchFragment fragment);
    void inject(AdasCalibrationSettingFragment fragment);
    void inject(AdasCalibrationSettingFittingFragment fragment);
    void inject(AdasCameraPositionSettingFragment fragment);
    void inject(AdasWarningSettingFragment fragment);
    void inject(AdasSettingFragment fragment);
    void inject(AdasTutorialFragment fragment);
    void inject(AdasBillingFragment fragment);
    void inject(IncomingCallPatternFragment fragment);
    void inject(IncomingCallColorFragment fragment);
    void inject(IncomingMessageColorFragment fragment);
    void inject(ParkingSensorFragment fragment);
    void inject(AlexaFragment fragment);
    void inject(AlexaSettingFragment fragment);
    void inject(AlexaSplashFragment fragment);
    void inject(AlexaExampleUsageFragment fragment);
    void inject(DebugSettingFragment fragment);
    void inject(ClassicBtDeviceListFragment fragment);
    void inject(VideoPlayerDialogFragment fragment);
    void inject(AdasUsageCautionFragment fragment);
    void inject(AdasManualFragment fragment);
    void inject(BackgroundImagePreviewFragment fragment);
    void inject(PromptAuthorityPermissionDialogFragment fragment);
    void inject(YouTubeLinkSettingFragment fragment);
    void inject(AppConnectMethodDialogFragment fragment);
}
